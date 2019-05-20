package ru.hh.school.checkup;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.school.checkup.dao.AccountDao;
import ru.hh.school.checkup.dao.BrowserCodeDao;
import ru.hh.school.checkup.dao.BrowserTestCheckDao;
import ru.hh.school.checkup.dao.ContestDao;
import ru.hh.school.checkup.dao.MessageDao;
import ru.hh.school.checkup.dao.SolutionDao;
import ru.hh.school.checkup.dao.TaskDao;
import ru.hh.school.checkup.dao.TestBatchDao;
import ru.hh.school.checkup.dao.TestDao;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.BrowserCode;
import ru.hh.school.checkup.entity.BrowserTestCheck;
import ru.hh.school.checkup.entity.Contest;
import ru.hh.school.checkup.entity.Message;
import ru.hh.school.checkup.entity.Participation;
import ru.hh.school.checkup.entity.Role;
import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.Task;
import ru.hh.school.checkup.entity.TaskDescription;
import ru.hh.school.checkup.entity.Test;
import ru.hh.school.checkup.entity.TestBatch;
import ru.hh.school.checkup.resource.tmp.Todo;
import ru.hh.school.checkup.resource.tmp.TodoDAO;
import ru.hh.school.checkup.resource.tmp.TodoService;
import ru.hh.school.checkup.service.AccountService;
import ru.hh.school.checkup.service.AuthorisationService;
import ru.hh.school.checkup.service.ContestService;
import ru.hh.school.checkup.service.MessageService;
import ru.hh.school.checkup.service.SolutionCheckerService;
import ru.hh.school.checkup.service.SolutionService;
import ru.hh.school.checkup.service.TaskService;
import ru.hh.school.checkup.service.TestBatchService;
import ru.hh.school.checkup.service.TestService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Import(value = {AccountService.class, TodoService.class, ContestService.class, MessageService.class,
        SolutionCheckerService.class, SolutionService.class, TaskService.class, TestService.class,
        TestBatchService.class, AuthorisationService.class,
        AccountDao.class, BrowserCodeDao.class, ContestDao.class, MessageDao.class, SolutionDao.class,
        TaskDao.class, TestDao.class, TodoDAO.class, TestBatchDao.class, BrowserTestCheckDao.class})
public class CheckupConfig {
    @Bean
    MappingConfig mappingConfig() {
        return new MappingConfig(
                Todo.class, Contest.class, Task.class,
                Test.class, Solution.class, Account.class,
                Message.class, Participation.class, BrowserCode.class,
                TaskDescription.class, TestBatch.class, BrowserTestCheck.class, Role.class);
    }

    @Bean
    ExecutorService taskLaunchExecutor() {
        // TODO number of threads and queue capacity put in service.properties
        ExecutorService executorService =  new ThreadPoolExecutor(4, 4, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));
        return executorService;

    }

    @Bean
    ExecutorService testLaunchExecutor() {
        // TODO number of threads put to service.properties
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        return executorService;
    }


}
