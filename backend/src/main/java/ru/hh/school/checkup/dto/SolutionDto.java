package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hh.school.checkup.entity.BrowserCode;
import ru.hh.school.checkup.entity.ProgLanguage;
import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.SolutionStatus;

import java.sql.Timestamp;

public class SolutionDto {

    public String solutionText;
    public ProgLanguage progLanguage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SolutionStatus statusId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Timestamp creationDate;

    public SolutionDto() {

    }

    public SolutionDto(Solution solution) {
        solutionText = solution.getSolutionText();
        progLanguage = solution.getProgLanguage();
        statusId = solution.getStatusId();
        creationDate = solution.getCreationDate();
    }

    public SolutionDto(BrowserCode browserCode) {
        solutionText = browserCode.getSolutionText();
        progLanguage = browserCode.getLanguage();
    }
}
