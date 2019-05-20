package ru.hh.school.checkup.service;


import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.MessageDao;
import ru.hh.school.checkup.dto.MessageDto;
import ru.hh.school.checkup.dto.MessageQuantityDto;
import ru.hh.school.checkup.dto.MessageTitlesDto;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Contest;
import ru.hh.school.checkup.entity.Message;
import ru.hh.school.checkup.entity.Participation;
import ru.hh.school.checkup.entity.Task;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageService {

    private MessageDao messageDAO;
    private AccountService accountService;
    private TaskService taskService;
    private ContestService contestService;

    @Inject
    public MessageService(MessageDao messageDAO, AccountService accountService,
                          TaskService taskService, ContestService contestService) {
        this.messageDAO = messageDAO;
        this.accountService = accountService;
        this.taskService = taskService;
        this.contestService = contestService;
    }

    @Transactional
    public MessageQuantityDto getQuantityUnreadMessages() {
        Account account = accountService.getCurrentUserAccount();
        Long unreadMessages = messageDAO.getUnreadMessagesCountForAccount(account);
        return new MessageQuantityDto(unreadMessages);
    }

    @Transactional
    public List<MessageDto> getMessagesForTask(Integer taskId) {
        Account account = accountService.getCurrentUserAccount();
        Task task = taskService.getTaskEntityById(taskId);
        List<Message> messages = messageDAO.getAllMessagesForTask(account, task);
        List<MessageDto> messageDtoList = messages.stream()
                .map(message -> new MessageDto(message, account))
                .collect(Collectors.toList());
        return messageDtoList;
    }

    @Transactional
    public void markMessagesAsWatched(Integer taskId) {
        Account account = accountService.getCurrentUserAccount();
        Task task = taskService.getTaskEntityById(taskId);
        messageDAO.markMessagesAsWatched(account, task);
    }

    @Transactional
    public void sendMessageOnTask(Integer taskId, MessageDto messageDTO) {
        Account senderAccount = accountService.getCurrentUserAccount();
        Account receiverAccount = accountService.getAdminAccount();
        Task task = taskService.getTaskEntityById(taskId);
        sendMessage(senderAccount, receiverAccount, task, messageDTO);
    }

    @Transactional
    public void sendMessageOnTaskByAdmin(Integer taskId, Integer receiverId, MessageDto messageDTO) {
        Account receiverAccount = accountService.getAccountById(receiverId);
        Account senderAccount = accountService.getAdminAccount();
        Task task = taskService.getTaskEntityById(taskId);
        sendMessage(senderAccount, receiverAccount, task, messageDTO);
    }

    private void sendMessage(Account sender, Account receiver, Task task, MessageDto messageDTO){
        Message message = new Message(sender, receiver, task, messageDTO);
        messageDAO.save(message);
    }

    @Transactional
    public List<MessageTitlesDto> messagesQuantityWithTitles() {
        Account account = accountService.getCurrentUserAccount();
        Participation participation = contestService.getUserActiveParticipation();
        Contest contest = participation.getContest();
        List<Object[]> result = messageDAO.unreadMsgsQuantityForEachTopic(account, contest);
        List<MessageTitlesDto> returnResult = result.stream()
                                    .map(arr -> new MessageTitlesDto(arr))
                                    .collect(Collectors.toList());
        return returnResult;
    }

    @Transactional
    public void markMessagesAsUnWatched(Integer taskId) {
        Account account = accountService.getCurrentUserAccount();
        Task task = taskService.getTaskEntityById(taskId);
        messageDAO.markMessagesAsUnWatched(account, task);
    }
}
