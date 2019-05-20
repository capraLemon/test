package ru.hh.school.checkup.resource.admin;

import ru.hh.school.checkup.dto.ContestDto;
import ru.hh.school.checkup.dto.TaskDto;
import ru.hh.school.checkup.service.ContestService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api/admin/contest")
public class AdminContestResouce {

    private ContestService contestService;

    @Inject
    public AdminContestResouce(ContestService contestService) {
        this.contestService = contestService;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createContest(ContestDto contestDTO) {
        contestService.createContest(contestDTO);
    }

    @GET
    @Path("/{id}")
    public ContestDto getContest(@PathParam("id") Integer id){
        return contestService.getContestById(id);
    }

    @GET
    @Path("/list")
    public List<ContestDto> getAllContests() {
        return contestService.getContests();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateContest(@PathParam("id") Integer id, ContestDto contestDTO) {
        contestService.updateContest(id, contestDTO);
    }

    @DELETE
    @Path("/{id}")
    public void deleteContest(@PathParam("id") Integer id) {
        contestService.deleteContestById(id);
    }

    @GET
    @Path("/{id}/tasks")
    public List<TaskDto> getContestTasks(@PathParam("id") Integer id) {
        List<TaskDto> tasks = contestService.getTasksForContest(id);
        return tasks;
    }

    @POST
    @Path("/{id}/addtask")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTask(@PathParam("id") Integer id, TaskDto taskDTO) {
        contestService.createTask(id, taskDTO);
    }
}
