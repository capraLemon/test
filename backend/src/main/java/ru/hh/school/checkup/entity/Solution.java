package ru.hh.school.checkup.entity;


import ru.hh.school.checkup.dto.SolutionDto;

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
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="solution")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="solution_id")
    private Integer solutionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name="status_id")
    @Enumerated(EnumType.STRING)
    private SolutionStatus statusId;

    @Column(name="solution_text")
    private String solutionText;

    @Column(name="language_id")
    @Enumerated(EnumType.STRING)
    private ProgLanguage progLanguage;

    @Column(name="creation_date")
    private Timestamp creationDate;


    public Solution() {
    }

    public Solution(SolutionDto solutionDTO) {
        solutionText = solutionDTO.solutionText;
        statusId = SolutionStatus.IN_QUEUE;
        progLanguage = solutionDTO.progLanguage;
        creationDate = new Timestamp(new Date().getTime());
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public SolutionStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(SolutionStatus statusId) {
        this.statusId = statusId;
    }

    public String getSolutionText() {
        return solutionText;
    }

    public void setSolutionText(String solutionText) {
        this.solutionText = solutionText;
    }

    public ProgLanguage getProgLanguage() {
        return progLanguage;
    }

    public void setProgLanguage(ProgLanguage progLanguage) {
        this.progLanguage = progLanguage;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
