package ru.hh.school.checkup.resource.admin;


import ru.hh.school.checkup.dto.TaskDto;
import ru.hh.school.checkup.dto.TestDto;
import ru.hh.school.checkup.service.TaskService;

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
import java.util.List;

@Path("/api/admin/task")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AdminTaskResource {

    private TaskService taskService;

    @Inject
    public AdminTaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @Path("/{id}")
    public TaskDto getTask(@PathParam("id") Integer id){
        return taskService.getTaskById(id);
    }

    @GET
    @Path("/list")
    public List<TaskDto> getAllTasks() {
        return taskService.getTasksForAdmin();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateContest(@PathParam("id") Integer id, TaskDto taskDTO) {
        taskService.updateTask(id, taskDTO);
    }


    @DELETE
    @Path("/{id}")
    public void deleteTask(@PathParam("id") Integer id) {
        taskService.deleteTaskById(id);
    }

    @GET
    @Path("/{id}/tests")
    public List<TestDto> getTaskTests(@PathParam("id") Integer id) {
        List<TestDto> tests = taskService.getTestsForTask(id);
        return tests;
    }

    @POST
    @Path("/{id}/addtest")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTest(@PathParam("id") Integer id, TestDto testDTO) {
       taskService.createTest(id, testDTO);
    }
}
