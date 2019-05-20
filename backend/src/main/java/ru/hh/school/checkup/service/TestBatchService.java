package ru.hh.school.checkup.service;


import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.TestBatchDao;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.BrowserTestCheck;
import ru.hh.school.checkup.entity.SolutionStatus;
import ru.hh.school.checkup.entity.Test;
import ru.hh.school.checkup.entity.TestBatch;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.exception.ErrorMessages;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
public class TestBatchService {

    private TestBatchDao testBatchDao;
    private AccountService accountService;

    @Inject
    public TestBatchService(TestBatchDao testBatchDao, AccountService accountService) {
        this.testBatchDao = testBatchDao;
        this.accountService = accountService;
    }


    @Transactional
    public TestBatch createTestBatch(Test userTest) {
        TestBatch testBatch = new TestBatch();
        BrowserTestCheck browserTestCheck = new BrowserTestCheck(testBatch, userTest);
        testBatch.getBrowserTestChecks().add(browserTestCheck);
        testBatchDao.save(testBatch);
        return testBatch;
    }

    @Transactional
    public TestBatch createTestBatch(List<Test> userTests) {
        Account account = accountService.getCurrentUserAccount();
        TestBatch testBatch = new TestBatch();
        testBatch.setAccount(account);
        userTests.forEach(test -> {
            BrowserTestCheck browserTestCheck = new BrowserTestCheck(testBatch, test);
            testBatch.getBrowserTestChecks().add(browserTestCheck);
        });
        testBatchDao.save(testBatch);
        return testBatch;
    }

    @Transactional
    public void abortTestsChecking(Integer testBatchId) {
        TestBatch testBatch = testBatchDao.findById(testBatchId);
        if (testBatch == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        Account userAccount = accountService.getCurrentUserAccount();
        if (!testBatch.getAccount().equals(userAccount)) {
            throw new CheckupException(Response.Status.FORBIDDEN, ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        testBatch.getBrowserTestChecks()
                .forEach(testCheck -> testCheck.setStatusId(SolutionStatus.CANCELLED));
    }
}
