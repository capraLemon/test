package ru.hh.school.checkup.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task_description")
public class TaskDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_description_id")
    private Integer taskDescriptionId;

    @Column(name = "sample_input")
    private String sampleInput;

    @Column(name = "sample_output")
    private String sampleOutput;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Integer getTaskDescriptionId() {
        return taskDescriptionId;
    }

    public void setTaskDescriptionId(Integer taskDescriptionId) {
        this.taskDescriptionId = taskDescriptionId;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
