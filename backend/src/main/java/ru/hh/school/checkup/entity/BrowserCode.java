package ru.hh.school.checkup.entity;

import ru.hh.school.checkup.dto.SolutionDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "browser_code")
public class BrowserCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "browser_code_id")
    private Integer browserCodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "solution_text")
    private String solutionText;

    @Column(name = "language_id")
    private ProgLanguage language;

    public BrowserCode() {
    }

    public BrowserCode(Account account, Task task, ProgLanguage language, String solutionText) {
        this.account = account;
        this.task = task;
        this.language = language;
        this.solutionText = solutionText;

    }

    public Integer getBrowserCodeId() {
        return browserCodeId;
    }

    public void setBrowserCodeId(Integer browserCodeId) {
        this.browserCodeId = browserCodeId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getSolutionText() {
        return solutionText;
    }

    public void setSolutionText(String solutionText) {
        this.solutionText = solutionText;
    }

    public ProgLanguage getLanguage() {
        return language;
    }

    public void setLanguage(ProgLanguage language) {
        this.language = language;
    }

    public void update(SolutionDto solutionDTO) {
        if(solutionDTO.progLanguage != null) {
            this.language = solutionDTO.progLanguage;
        }
        if(solutionDTO.solutionText != null) {
            this.solutionText = solutionDTO.solutionText;
        }
    }
}
