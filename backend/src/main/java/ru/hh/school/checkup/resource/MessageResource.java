package ru.hh.school.checkup.resource;

import ru.hh.school.checkup.dto.MessageDto;
import ru.hh.school.checkup.dto.MessageQuantityDto;
import ru.hh.school.checkup.dto.MessageTitlesDto;
import ru.hh.school.checkup.service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/message")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MessageResource {

    private MessageService messageService;

    @Inject
    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GET
    @Path("/unread")
    public MessageQuantityDto getQuantityOfUnreadMessages() {
        return messageService.getQuantityUnreadMessages();
    }

    @GET
    @Path("/byTopic/{taskId}")
    public List<MessageDto> getMessagesByTopic(@PathParam("taskId") Integer taskId) {
        return messageService.getMessagesForTask(taskId);
    }

    @GET
    @Path("/byTask/{taskId}")
    public List<MessageDto> getMessagesByTask(@PathParam("taskId") Integer taskId) {
        return messageService.getMessagesForTask(taskId);
    }

    @PUT
    @Path("/read/{taskId}")
    public void readMessagesForTask(@PathParam("taskId") Integer taskId) {
        messageService.markMessagesAsWatched(taskId);
    }

    @POST
    @Path("/byTask/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendMessageOnTask(@PathParam("taskId") Integer taskId, MessageDto messageDTO) {
        messageService.sendMessageOnTask(taskId, messageDTO);
    }

    @POST
    @Path("/byTopic/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendMessageOnTopic(@PathParam("taskId") Integer taskId, MessageDto messageDTO) {
        messageService.sendMessageOnTask(taskId, messageDTO);
    }

    @GET
    @Path("/topics")
    public List<MessageTitlesDto> getQuantityOfMessagesWithTitles() {
        return messageService.messagesQuantityWithTitles();
    }
}
