package ru.hh.school.checkup.resource;

import ru.hh.school.checkup.dto.SeparateTestsDto;
import ru.hh.school.checkup.dto.SolutionDto;
import ru.hh.school.checkup.dto.TestBatchDto;
import ru.hh.school.checkup.dto.TestStatusDto;
import ru.hh.school.checkup.dto.UserTestDto;
import ru.hh.school.checkup.entity.Roles;
import ru.hh.school.checkup.service.AuthorisationService;
import ru.hh.school.checkup.service.SolutionCheckerService;
import ru.hh.school.checkup.service.TestBatchService;
import ru.hh.school.checkup.service.TestService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/api/test")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TestResource {

    private TestService testService;
    private SolutionCheckerService solutionCheckerService;
    private TestBatchService testBatchService;
    private AuthorisationService authorisationService;

    @Inject
    public TestResource(TestService testService, SolutionCheckerService solutionCheckerService,
                        TestBatchService testBatchService,
                        AuthorisationService authorisationService) {
        this.testService = testService;
        this.solutionCheckerService = solutionCheckerService;
        this.testBatchService = testBatchService;
        this.authorisationService = authorisationService;
    }

    @Path("/{taskId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserTestDto createUserTest(@PathParam("taskId") Integer taskId, UserTestDto userTestDto) {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        return new UserTestDto(testService.createUserTest(taskId, userTestDto));
    }

    @Path("/{taskId}/list")
    @GET
    public SeparateTestsDto getSmokeTestsForUser(@PathParam("taskId")Integer taskId) {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        return testService.getSmokeTestsForUser(taskId);
    }

    @Path("/{testId}")
    @GET
    public UserTestDto getUserTest(@PathParam("testId")Integer testId) {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        return testService.getSmokeTest(testId);
    }

    @Path("/{testId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifyUserTest(@PathParam("testId")Integer testId, UserTestDto userTestDto) {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        testService.modifyUsersTest(testId, userTestDto);
    }

    @Path("/{testId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUserTest(@PathParam("testId")Integer testId) {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        testService.deleteUsersTest(testId);
    }

    @Path("/run/{testId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public TestStatusDto runUserTest(@PathParam("testId")Integer testId, SolutionDto solutionDto) throws IOException {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        return solutionCheckerService.runSingleTest(testId, solutionDto);
    }

    @Path("/{taskId}/runAll")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public TestBatchDto runAllUserTests(@PathParam("taskId")Integer taskId, SolutionDto solutionDto) throws IOException {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        return solutionCheckerService.runAllUserTests(taskId, solutionDto);
    }

    @Path("/run/{testQueueId}")
    @GET
    public TestStatusDto getTestStatus(@PathParam("testQueueId")Integer testQueueId){
        authorisationService.verifyHasAuthorisation(Roles.USER);
        return testService.getTestStatusByQueueId(testQueueId);
    }

    @Path("/abortBatch/{testBatchId}")
    @DELETE
    public void abortBrowserTestsBatch(@PathParam("testBatchId") Integer testBatchId) {
        authorisationService.verifyHasAuthorisation(Roles.USER);
        testBatchService.abortTestsChecking(testBatchId);
    }
}
