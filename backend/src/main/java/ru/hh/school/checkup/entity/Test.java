package ru.hh.school.checkup.entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.hh.school.checkup.dto.TestDto;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "test")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Test {

    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "test_input")
    private String testInput;

    @Column(name = "expected_output")
    private String expectedOutput;

    @Column(name = "is_smoke")
    private Boolean isSmoke;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", referencedColumnName = "account_id")
    private Account author;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<BrowserTestCheck> browserTestCheck = new ArrayList<>();

    public Test() {
    }

    public Test(TestDto testDTO) {
        testInput = testDTO.testInput;
        expectedOutput = testDTO.expectedOutput;
        isSmoke = testDTO.isSmoke;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTestInput() {
        return testInput;
    }

    public void setTestInput(String testInput) {
        this.testInput = testInput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public Boolean getSmoke() {
        return isSmoke;
    }

    public void setSmoke(Boolean smoke) {
        isSmoke = smoke;
    }

    @Override
    public boolean equals(Object o) {
        return Objects.equals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, testInput, expectedOutput);
    }

    public void updateFields(TestDto testDTO) {
        if (testDTO.testInput != null) {
            testInput = testDTO.testInput;
        }
        if (testDTO.expectedOutput != null) {
            expectedOutput = testDTO.expectedOutput;
        }
        if (testDTO.isSmoke != null) {
            isSmoke = testDTO.isSmoke;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public List<BrowserTestCheck> getBrowserTestCheck() {
        return browserTestCheck;
    }

    public void setBrowserTestCheck(List<BrowserTestCheck> browserTestCheck) {
        this.browserTestCheck = browserTestCheck;
    }
}
