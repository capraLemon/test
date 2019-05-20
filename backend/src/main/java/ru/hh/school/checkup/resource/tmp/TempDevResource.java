package ru.hh.school.checkup.resource.tmp;

import ru.hh.school.checkup.dto.MessageDto;
import ru.hh.school.checkup.service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("/api/dev")
public class TempDevResource {

    private MessageService messageService;

    @Inject
    public TempDevResource(MessageService messageService) {
        this.messageService = messageService;
    }

    // TODO endpoint for admin only or testing
    @POST
    @Path("/message/byTask/{taskId}/{receiverId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendMessageOnTaskAdmin(@PathParam("taskId") Integer taskId,
                                       @PathParam("receiverId") Integer receiverId,
                                       MessageDto messageDTO) {
        messageService.sendMessageOnTaskByAdmin(taskId, receiverId, messageDTO);
    }

    @PUT
    @Path("/message/unread/{taskId}")
    public void readMessagesForTask(@PathParam("taskId") Integer taskId) {
        messageService.markMessagesAsUnWatched(taskId);
    }
}
