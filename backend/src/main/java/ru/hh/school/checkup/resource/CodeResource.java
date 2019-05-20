package ru.hh.school.checkup.resource;

import ru.hh.school.checkup.dto.SolutionDto;
import ru.hh.school.checkup.dto.SolutionStatusDto;
import ru.hh.school.checkup.service.SolutionService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/code")
@Produces(MediaType.APPLICATION_JSON)
public class CodeResource {

    private SolutionService solutionService;

    @Inject
    public CodeResource(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @POST
    @Path("/send/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public SolutionStatusDto sendSolution(@PathParam("taskId") Integer taskId, SolutionDto solutionDTO) {
        return solutionService.processSolution(solutionDTO, taskId);
    }

    @GET
    @Path("/history/{taskId}")
    public List<SolutionStatusDto> getUserSolutionsByTask(@PathParam("taskId") Integer taskId){
        return solutionService.getUserSolutionsByTask(taskId);
    }

    @GET
    @Path("/history/details/{solutionId}")
    public SolutionDto getSolutionDetails(@PathParam("solutionId") Integer solutionId) {
        return solutionService.getSolutionByIdForUser(solutionId);
    }

    @GET
    @Path("/current/{taskId}")
    public SolutionDto getBrowserSolution(@PathParam("taskId") Integer taskId) {
        return solutionService.getBrowserSolution(taskId);
    }

    @POST
    @Path("/current/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setBrowserSolution(@PathParam("taskId") Integer taskId, SolutionDto browserCode) {
        solutionService.setBrowserSolution(taskId, browserCode);
    }


}
