package ru.hh.school.checkup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "browser_test_check")
public class BrowserTestCheck {

    @Id
    @Column(name = "browser_test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer browserTestId;

    @ManyToOne
    @JoinColumn(name = "test_batch_id")
    private TestBatch testBatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "status_id")
    @Enumerated(EnumType.STRING)
    private SolutionStatus statusId;

    @Column(name = "test_output")
    private String testOutput;

    public BrowserTestCheck() {

    }

    public BrowserTestCheck(TestBatch testBatch, Test userTest) {
        this.testBatch = testBatch;
        this.test = userTest;
        this.statusId = SolutionStatus.IN_QUEUE;
    }

    public Integer getBrowserTestId() {
        return browserTestId;
    }

    public void setBrowserTestId(Integer browserTestId) {
        this.browserTestId = browserTestId;
    }

    public TestBatch getTestBatch() {
        return testBatch;
    }

    public void setTestBatch(TestBatch testBatch) {
        this.testBatch = testBatch;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public SolutionStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(SolutionStatus statusId) {
        this.statusId = statusId;
    }

    public String getTestOutput() {
        return testOutput;
    }

    public void setTestOutput(String testOutput) {
        this.testOutput = testOutput;
    }
}

