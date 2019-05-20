package ru.hh.school.checkup.dto;

import ru.hh.school.checkup.entity.TaskDescription;

public class TaskDescriptionDto {

    public String input;
    public String output;
    public String notes;

    public TaskDescriptionDto() {

    }

    public TaskDescriptionDto(TaskDescription taskDescription){
        input = taskDescription.getSampleInput();
        output = taskDescription.getSampleOutput();
        notes = taskDescription.getNotes();
    }
}
