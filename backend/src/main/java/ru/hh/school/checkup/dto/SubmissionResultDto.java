package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SubmissionResultDto implements Serializable {
    public String stdout;
    public SubmissionStatusDto status;
    public String time;
    public Integer memory;
    public String stderr;
    public String token;
    @JsonProperty("compile_output")
    public String compileOutput;
    public String message;
}



