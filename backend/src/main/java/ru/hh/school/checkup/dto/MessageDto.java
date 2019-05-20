package ru.hh.school.checkup.dto;

import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Message;

import java.sql.Timestamp;

public class MessageDto {

    public String messageText;
    public Timestamp creationDate;
    public Boolean isWatched;
    public Boolean senderIsUser;

    public MessageDto() {

    }

    public MessageDto(Message message, Account userAccount) {
        messageText = message.getMessageText();
        creationDate = message.getCreationDate();
        isWatched = message.getWatched();
        senderIsUser = userAccount.equals(message.getSenderAccount());
    }
}
