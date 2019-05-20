package ru.hh.school.checkup.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "test_batch")
public class TestBatch {

    @Id
    @Column(name = "test_batch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testBatchId;

    @OneToMany(mappedBy = "testBatch", cascade = CascadeType.ALL)
    private List<BrowserTestCheck> browserTestChecks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Integer getTestBatchId() {
        return testBatchId;
    }

    public void setTestBatchId(Integer testBatchId) {
        this.testBatchId = testBatchId;
    }

    public List<BrowserTestCheck> getBrowserTestChecks() {
        return browserTestChecks;
    }

    public void setBrowserTestChecks(List<BrowserTestCheck> browserTestChecks) {
        this.browserTestChecks = browserTestChecks;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
