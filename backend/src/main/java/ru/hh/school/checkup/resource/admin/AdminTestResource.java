package ru.hh.school.checkup.resource.admin;

import ru.hh.school.checkup.dto.TestDto;
import ru.hh.school.checkup.service.TestService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/admin/test")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AdminTestResource {

    private TestService testService;

    @Inject
    public AdminTestResource(TestService testService) {
        this.testService = testService;
    }

    @GET
    @Path("/{id}")
    public TestDto getTest(@PathParam("id") Integer id) {
        return testService.getTestById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTest(@PathParam("id") Integer id, TestDto testDTO) {
        testService.updateTest(id, testDTO);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTest(@PathParam("id") Integer id) {
        testService.deleteTest(id);
    }
}
