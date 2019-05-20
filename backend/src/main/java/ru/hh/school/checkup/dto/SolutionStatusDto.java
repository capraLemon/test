package ru.hh.school.checkup.dto;

import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.SolutionStatus;

import java.sql.Timestamp;

public class SolutionStatusDto {

    public Integer solutionId;
    public SolutionStatus statusId;
    public Boolean isChecking;
    public Timestamp creationTime;

    public SolutionStatusDto(Solution solution) {
        solutionId = solution.getSolutionId();
        statusId = solution.getStatusId();
        isChecking = statusId == SolutionStatus.SAVING
                        || statusId == SolutionStatus.PROCESSING
                        || statusId == SolutionStatus.IN_QUEUE;
        creationTime = solution.getCreationDate();

    }
}
