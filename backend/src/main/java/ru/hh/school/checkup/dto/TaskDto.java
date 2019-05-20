package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hh.school.checkup.entity.Task;

import java.util.List;

public class TaskDto {
    public Integer taskId;
    public String taskText;
    public Integer timeLimit;
    public Integer memoryLimit;
    public Integer triesLimit;
    public String title;
    public Boolean isChat;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<TaskDescriptionDto> sampleInputOutput;

    public TaskDto() {

    }
    public TaskDto(Task task) {
        taskId = task.getTaskId();
        taskText = task.getTaskText();
        timeLimit = task.getTimeLimit();
        memoryLimit = task.getMemoryLimit();
        triesLimit = task.getTriesLimit();
        title = task.getTitle();
        isChat = task.getIsChat();
    }
    public TaskDto(Task task, List<TaskDescriptionDto> taskDescriptionDto) {
        this(task);
        sampleInputOutput = taskDescriptionDto;
    }

}
