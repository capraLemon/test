package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.Test;

public class SubmissionDto {
    //https://api.judge0.com/#system-and-configuration-configuration-info
    // Limit size of files created (or modified) by the program.
    private static final Integer MAX_FILE_SIZE = 4096;
    @JsonProperty("source_code")
    public String sourceCode;
    @JsonProperty("max_file_size")
    public Integer maxFileSize;
    @JsonProperty("cpu_time_limit")
    public Integer cpuTimeLimit;
    @JsonProperty("language_id")
    public Integer languageId;
    public String stdin;
    @JsonProperty("expected_output")
    public String expectedOutput;

    public SubmissionDto(Solution solution, Test test) {
        sourceCode = solution.getSolutionText();
        maxFileSize = MAX_FILE_SIZE;
        cpuTimeLimit = test.getTask().getTimeLimit();
        languageId = solution.getProgLanguage().getLanguageId();
        stdin = test.getTestInput();
        expectedOutput = test.getExpectedOutput();
    }
}
