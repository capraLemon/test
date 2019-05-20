package ru.hh.school.checkup.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.hh.school.checkup.dto.TaskDto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();

    @Column(name="task_text")
    private String taskText;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "time_limit")
    private Integer timeLimit;

    @Column(name = "memory_limit")
    private Integer memoryLimit;

    @Column(name = "tries_limit")
    private Integer triesLimit;

    @Column(name = "title")
    private String title;

    @Column(name = "is_chat")
    private Boolean isChat;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();


    @OneToMany(mappedBy="task", cascade = CascadeType.ALL)
    private List<Solution> solutions = new ArrayList<>();

    public Task() {

    }
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskDescription> taskDescriptions = new ArrayList<>();

    public Task(TaskDto taskDTO) {
        taskText = taskDTO.taskText;
        timeLimit = taskDTO.timeLimit;
        memoryLimit = taskDTO.memoryLimit;
        title = taskDTO.title;
        triesLimit =taskDTO.triesLimit;
        isChat = taskDTO.isChat;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Integer getTriesLimit() {
        return triesLimit;
    }

    public void setTriesLimit(Integer triesLimit) {
        this.triesLimit = triesLimit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }



    public void updateFields(TaskDto taskDTO) {
        if (taskDTO.taskText != null) {
            taskText = taskDTO.taskText;
        }
        if (taskDTO.timeLimit != null) {
            timeLimit = taskDTO.timeLimit;
        }
        if (taskDTO.memoryLimit != null) {
            memoryLimit = taskDTO.memoryLimit;
        }
        if (taskDTO.title != null) {
            title = taskDTO.title;
        }
        if (triesLimit != null) {
            triesLimit = taskDTO.triesLimit;
        }
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public Boolean getIsChat() {
        return isChat;
    }

    public void setIsChat(Boolean isChat) {
        isChat = isChat;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<TaskDescription> getTaskDescriptions() {
        return taskDescriptions;
    }

    public void setTaskDescriptions(List<TaskDescription> taskDescriptions) {
        this.taskDescriptions = taskDescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (!taskId.equals(task.taskId)) {
            return false;
        }
        if (!creationDate.equals(task.creationDate)) {
            return false;
        }
        if (!timeLimit.equals(task.timeLimit)) {
            return false;
        }
        if (!memoryLimit.equals(task.memoryLimit)) {
            return false;
        }
        if (!triesLimit.equals(task.triesLimit)) {
            return false;
        }
        return isChat.equals(task.isChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, creationDate, timeLimit, memoryLimit, triesLimit, isChat);
    }
}
