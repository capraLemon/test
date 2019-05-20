package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hh.school.checkup.entity.BrowserTestCheck;
import ru.hh.school.checkup.entity.SolutionStatus;

public class TestStatusDto implements Comparable<TestStatusDto> {
    public Integer testId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SolutionStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer testQueueId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String testOutput;

    public TestStatusDto(Integer testId, SolutionStatus status) {
        this.testId = testId;
        this.status = status;
    }

    public TestStatusDto(Integer testId, Integer browserTestId) {
        this.testId = testId;
        this.testQueueId = browserTestId;
    }

    public TestStatusDto(BrowserTestCheck browserTestCheck) {
        this.testId = browserTestCheck.getTest().getTestId();
        this.status = browserTestCheck.getStatusId();
        this.testOutput = browserTestCheck.getTestOutput();
    }

    public TestStatusDto() {
    }

    @Override
    public int compareTo(TestStatusDto o) {
        return testId - o.testId;
    }
}
