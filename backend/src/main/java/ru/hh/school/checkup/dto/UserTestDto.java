package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hh.school.checkup.entity.Test;


public class UserTestDto implements Comparable<UserTestDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String testInput;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String testExpectedOutput;
    public String testTitle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer testId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer taskId;

    public UserTestDto(Test test) {
        testTitle = test.getTitle();
        testId = test.getTestId();
    }

    public UserTestDto() {

    }

    @Override
    public int compareTo(UserTestDto o) {
        return testId - o.testId;
    }
}
