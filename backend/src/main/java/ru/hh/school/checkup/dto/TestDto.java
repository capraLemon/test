package ru.hh.school.checkup.dto;

import ru.hh.school.checkup.entity.Test;

public class TestDto {
    public Integer testId;
    public String testInput;
    public String expectedOutput;
    public Boolean isSmoke;

    public TestDto() {

    }

    public TestDto(Test test) {
        testId = test.getTestId();
        testInput = test.getTestInput();
        expectedOutput = test.getExpectedOutput();
        isSmoke = test.getSmoke();
    }
}
