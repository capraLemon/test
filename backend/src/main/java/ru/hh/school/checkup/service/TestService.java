package ru.hh.school.checkup.service;

import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.BrowserTestCheckDao;
import ru.hh.school.checkup.dao.TestDao;
import ru.hh.school.checkup.dto.SeparateTestsDto;
import ru.hh.school.checkup.dto.TestDto;
import ru.hh.school.checkup.dto.TestStatusDto;
import ru.hh.school.checkup.dto.UserTestDto;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.BrowserTestCheck;
import ru.hh.school.checkup.entity.Task;
import ru.hh.school.checkup.entity.Test;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.exception.ErrorMessages;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestService {

    private TestDao testDAO;
    private AccountService accountService;
    private TaskService taskService;
    private BrowserTestCheckDao browserTestCheckDao;

    @Inject
    public TestService(TestDao testDAO, AccountService accountService, TaskService taskService, BrowserTestCheckDao browserTestCheckDao) {
        this.testDAO = testDAO;
        this.accountService = accountService;
        this.taskService = taskService;
        this.browserTestCheckDao = browserTestCheckDao;
    }

    @Transactional
    public TestDto getTestById(Integer id) {
        Test storedTest = getTestEntityById(id);
        return new TestDto(storedTest);
    }

    private Test getTestEntityById(Integer id)  {
        Test testEntity = testDAO.findById(id);
        if (testEntity == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return testEntity;
    }

    @Transactional
    public void updateTest(Integer id, TestDto testDTO) {
        Test storedTest = getTestEntityById(id);
        storedTest.updateFields(testDTO);
    }

    @Transactional
    public void deleteTest(Integer id) {
        Test storedTest = getTestEntityById(id);
        testDAO.delete(storedTest);
    }

    @Transactional
    public Test createUserTest(Integer taskId, UserTestDto userTestDto) {
        Account userAccount = accountService.getCurrentUserAccount();
        Task task = taskService.getTaskEntityById(taskId);
        Test newTest = new Test();
        newTest.setTask(task);
        newTest.setAuthor(userAccount);
        newTest.setTestInput(userTestDto.testInput);
        newTest.setExpectedOutput(userTestDto.testExpectedOutput);
        newTest.setSmoke(true);
        newTest.setTitle(userTestDto.testTitle);
        task.getTests().add(newTest);
        return newTest;
    }

    @Transactional
    public SeparateTestsDto getSmokeTestsForUser(Integer taskId) {
        Account userAccount = accountService.getCurrentUserAccount();
        Account admin = accountService.getAdminAccount();
        List<Test> smokeTests = getSmokeTestEntitiesForTask(taskId);
        List<UserTestDto> userTests = smokeTests.stream()
                .filter(test -> test.getAuthor().equals(userAccount))
                .map(test -> new UserTestDto(test))
                .collect(Collectors.toList());
        List<UserTestDto> adminTests = smokeTests.stream()
                .filter(test -> test.getAuthor().equals(admin))
                .map(test -> new UserTestDto(test))
                .collect(Collectors.toList());
        return new SeparateTestsDto(userTests, adminTests);
    }

    @Transactional
    public List<Test> getSmokeTestEntitiesForTask(Integer taskId) {
        Account userAccount = accountService.getCurrentUserAccount();
        Account admin = accountService.getAdminAccount();
        Task task = taskService.getTaskEntityById(taskId);
        List<Test> smokeTests =  testDAO.findSmokeTestsForTask(userAccount, admin, task);
        if (smokeTests == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return smokeTests;
    }


    @Transactional
    public void modifyUsersTest(Integer testId, UserTestDto userTestDto) {
        Test userTest = getUserTestById(testId);
        if (userTestDto.testInput != null) {
            userTest.setTestInput(userTestDto.testInput);
        }
        if (userTestDto.testExpectedOutput != null) {
            userTest.setExpectedOutput(userTestDto.testExpectedOutput);
        }
        if (userTestDto.testTitle != null) {
            userTest.setTitle(userTestDto.testTitle);
        }
    }

    @Transactional
    public Test getUserTestById(Integer testId){
        Account userAccount = accountService.getCurrentUserAccount();
        Test userTest = getTestEntityById(testId);
        if (!userTest.getAuthor().equals(userAccount)) {
            throw new CheckupException(Response.Status.FORBIDDEN, ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        return userTest;
    }


    @Transactional
    public void deleteUsersTest(Integer testId) {
        Test userTest = getUserTestById(testId);
        testDAO.delete(userTest);
    }

    @Transactional
    public TestStatusDto getTestStatusByQueueId(Integer testQueueId) {
        BrowserTestCheck browserTestCheck =  browserTestCheckDao.findById(testQueueId);
        if (browserTestCheck == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return new TestStatusDto(browserTestCheck);
    }

    @Transactional
    public UserTestDto getSmokeTest(Integer testId) {
        Test userTest = null;
        try {
            userTest = getUserTestById(testId);
        } catch (CheckupException e) {
            userTest = getAdminSmokeTestById(testId);
        }
        UserTestDto returnValue = new UserTestDto();
        returnValue.testTitle = userTest.getTitle();
        returnValue.testInput = userTest.getTestInput();
        returnValue.testExpectedOutput = userTest.getExpectedOutput();
        returnValue.testId = userTest.getTestId();
        returnValue.taskId = userTest.getTask().getTaskId();
        return returnValue;
    }

    @Transactional
    public Test getAdminSmokeTestById(Integer testId){
        Account adminAccount = accountService.getAdminAccount();
        Test adminTest = getTestEntityById(testId);
        if (!adminTest.getAuthor().equals(adminAccount) || !adminTest.getSmoke()) {
            throw new CheckupException(Response.Status.FORBIDDEN, ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        return adminTest;
    }

}
