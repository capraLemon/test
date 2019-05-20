package ru.hh.school.checkup.resource;


import ru.hh.school.checkup.dto.ContestDto;
import ru.hh.school.checkup.service.ContestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/contest")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ContestResource {

    private ContestService contestService;

    @Inject
    public ContestResource(ContestService contestService) {
        this.contestService = contestService;
    }

    @GET
    public ContestDto getContestForUser(){
        return contestService.getContestForUser();
    }

    @PUT
    @Path("/start")
    public void startParticipation() {
        contestService.startParticipation();
    }

    @GET
    @Path("/timeLeft")
    public TimerDto getTimeLeft() {
        return contestService.getTimeLeft();
    }
}
