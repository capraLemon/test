package ru.hh.school.checkup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.BrowserTestCheckDao;
import ru.hh.school.checkup.dto.SolutionDto;
import ru.hh.school.checkup.dto.SubmissionDto;
import ru.hh.school.checkup.dto.SubmissionResultDto;
import ru.hh.school.checkup.dto.TestBatchDto;
import ru.hh.school.checkup.dto.TestStatusDto;
import ru.hh.school.checkup.entity.BrowserTestCheck;
import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.SolutionStatus;
import ru.hh.school.checkup.entity.Test;
import ru.hh.school.checkup.entity.TestBatch;
import ru.hh.school.checkup.exception.CheckingServerIsNotAvailableException;
import ru.hh.school.checkup.exception.CheckupException;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Component
public class SolutionCheckerService {

    private static final int HTTP_CREATED_201_STATUS = 201;
    private Properties serviceProperties;
    private SolutionService solutionService;
    private ExecutorService taskLaunchExecutor;
    private TestService testService;
    private ExecutorService testLaunchExecutor;
    private TestBatchService testBatchService;
    private BrowserTestCheckDao browserTestCheckDao;

    @Inject
    public SolutionCheckerService(Properties serviceProperties, SolutionService solutionService,
                                  ExecutorService taskLaunchExecutor, TestService testService,
                                  ExecutorService testLaunchExecutor,
                                  TestBatchService testBatchService,
                                  BrowserTestCheckDao browserTestCheckDao) {
        this.serviceProperties = serviceProperties;
        this.solutionService = solutionService;
        this.taskLaunchExecutor = taskLaunchExecutor;
        this.testService = testService;
        this.testLaunchExecutor = testLaunchExecutor;
        this.testBatchService = testBatchService;
        this.browserTestCheckDao = browserTestCheckDao;
    }

    @Scheduled(fixedDelay = 1000)
    public void checkSolutionFromQueue() {
        try {
            taskLaunchExecutor.execute(() -> {
                Optional<Solution> solution = solutionService.getUncheckedSolution();
                if (solution.isEmpty()) {
                    return;
                }
                solutionService.updateSolution(solution.get(), SolutionStatus.PROCESSING);
                SolutionStatus newSolutionStatus = processUncheckedSolution(solution.get());
                solutionService.updateSolution(solution.get(), newSolutionStatus);
            });
        } catch (RejectedExecutionException e) {
        }
    }

    private SolutionStatus processUncheckedSolution(Solution uncheckedSolution) {
        String judje0Url = serviceProperties.getProperty("checkupSpaceJudge0Url");
        SolutionStatus testStatus = SolutionStatus.ACCEPTED;
        try {
            for (Test test : uncheckedSolution.getTask().getTests()) {
                //TODO smoke test should not be checked
                if (test.getSmoke()) {
                    continue;
                }
                SubmissionResultDto submissionResultDto = sendSolutionForCheck(judje0Url, uncheckedSolution, test);
                testStatus = SolutionStatus.values()[submissionResultDto.status.id];
                if (testStatus != SolutionStatus.ACCEPTED) {
                    break;
                }
            }
            uncheckedSolution.setStatusId(testStatus);
        } catch (Exception e) {
            testStatus = SolutionStatus.IN_QUEUE;
            e.printStackTrace();
        }
        return testStatus;
    }

    private SubmissionResultDto sendSolutionForCheck(String judje0Url, Solution uncheckedSolution, Test test)
            throws java.io.IOException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(judje0Url);
        Invocation.Builder invocationBuilder =
                webTarget.request(MediaType.APPLICATION_JSON);
        SubmissionDto submissionDTO = new SubmissionDto(uncheckedSolution, test);
        Response response = invocationBuilder
                .post(Entity.entity(submissionDTO, MediaType.APPLICATION_JSON));
        //TODO handle exception java.net.NoRouteToHostException - when host is not reachable
        if (response.getStatus() != HTTP_CREATED_201_STATUS) {
            throw new CheckingServerIsNotAvailableException();
        }
        SubmissionResultDto submissionResult = new ObjectMapper()
                .readValue(response.readEntity(String.class), SubmissionResultDto.class);
        return submissionResult;
    }

    public TestStatusDto runSingleTest(Integer testId, SolutionDto solutionDto) throws IOException {
        solutionService.validateSolution(solutionDto);
        Test smokeTest;
        try {
            smokeTest = testService.getUserTestById(testId);
        } catch (CheckupException e) {
            smokeTest = testService.getAdminSmokeTestById(testId);
        }
        Solution userSolution = new Solution(solutionDto);
        TestBatch testBatch = testBatchService.createTestBatch(smokeTest);
        BrowserTestCheck browserTestCheck = testBatch.getBrowserTestChecks().get(0);
        handleSolution(smokeTest, userSolution, browserTestCheck);
        return new TestStatusDto(smokeTest.getTestId(), browserTestCheck.getBrowserTestId());
    }

    public void handleSolution(Test test, Solution userSolution, BrowserTestCheck browserTestCheck) {
        String judje0Url = serviceProperties.getProperty("checkupBrowserTestCheckerJudge0Url");
        testLaunchExecutor.execute(() -> {
            SolutionStatus status = null;
            try {
                BrowserTestCheck currentTestCheck = browserTestCheckDao.findById(browserTestCheck.getBrowserTestId());
                if (currentTestCheck.getStatusId().equals(SolutionStatus.CANCELLED)) {
                    return;
                }
                SubmissionResultDto submissionResult = sendSolutionForCheck(judje0Url, userSolution, test);
                status = SolutionStatus.values()[submissionResult.status.id];
                browserTestCheck.setTestOutput(submissionResult.stdout);
            } catch (IOException ex) {
                status = SolutionStatus.INTERNAL_ERROR;
            }
            browserTestCheck.setStatusId(status);
            browserTestCheckDao.save(browserTestCheck);
        });

    }

    public TestBatchDto runAllUserTests(Integer taskId, SolutionDto solutionDto) throws IOException {
        solutionService.validateSolution(solutionDto);
        List<Test> smokeTests = testService.getSmokeTestEntitiesForTask(taskId);
        Solution solution = new Solution(solutionDto);
        TestBatch testBatch = testBatchService.createTestBatch(smokeTests);
        List<TestStatusDto> testStatusDtos = testBatch.getBrowserTestChecks().stream()
                .map(t -> {
                    handleSolution(t.getTest(), solution, t);
                    return new TestStatusDto(t.getTest().getTestId(), t.getBrowserTestId());
                })
                .sorted()
                .collect(Collectors.toList());
        return new TestBatchDto(testBatch.getTestBatchId(), testStatusDtos);
    }
}
