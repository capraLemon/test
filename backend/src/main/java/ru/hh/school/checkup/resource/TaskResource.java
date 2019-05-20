package ru.hh.school.checkup.resource;


import ru.hh.school.checkup.dto.TaskDto;
import ru.hh.school.checkup.dto.TaskTitleDto;
import ru.hh.school.checkup.service.TaskService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private TaskService taskService;

    @Inject
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @Path("/list")
    @GET
    public List<TaskTitleDto> getTasksForUser() {
        return taskService.getTasksForUser();
    }

    @Path("/{taskId}")
    @GET
    public TaskDto getSingleTaskForUser(@PathParam("taskId") Integer taskId) {
        return taskService.getSingleTaskForUser(taskId);
    }
}
