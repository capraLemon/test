package ru.hh.school.checkup.dto;

import java.util.List;

public class TestBatchDto  {
    public Integer testBatchId;
    public List<TestStatusDto> testsStatuses;

    public TestBatchDto(Integer testBatchId, List<TestStatusDto> testStatusDtos) {
        this.testBatchId = testBatchId;
        this.testsStatuses = testStatusDtos;
    }
}
