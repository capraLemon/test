package ru.hh.school.checkup.dto;

import ru.hh.school.checkup.entity.Task;

public class TaskTitleDto {

    public Integer taskId;
    public String title;

    public TaskTitleDto() {

    }

    public TaskTitleDto(Task task) {
        taskId = task.getTaskId();
        title = task.getTitle();
    }
}
