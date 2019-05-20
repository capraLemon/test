package ru.hh.school.checkup.entity;


import ru.hh.school.checkup.dto.MessageDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "message_text")
    private String messageText;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "account_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "account_id")
    private Account receiverAccount;

    @ManyToOne
    @JoinColumn(name= "task_id")
    private Task task;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "is_watched")
    private Boolean isWatched;

    public Message() {

    }


    public Message(Account senderAccount, Account receiverAccount, Task task, MessageDto messageDTO) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.task = task;
        this.messageText = messageDTO.messageText;
        this.creationDate = new Timestamp(new Date().getTime());
        this.isWatched = false;
    }


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getWatched() {
        return isWatched;
    }

    public void setWatched(Boolean watched) {
        isWatched = watched;
    }
}
