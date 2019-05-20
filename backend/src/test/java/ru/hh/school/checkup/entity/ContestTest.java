package ru.hh.school.checkup.entity;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.checkup.TestConfig;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = TestConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ContestTest extends NabTestBase {

    @Inject
    SessionFactory sessionFactory;

    @Test
    public void canPersistContest() {
        Contest contest = createContest();
        sessionFactory.getCurrentSession().persist(contest);
        Integer savedId = contest.getContestId();
        assertEquals(contest.getName(),
                sessionFactory.getCurrentSession().get(Contest.class, savedId).getName());
    }

    @Test
    public void canPersistTask() {
        Task task = createTask();
        sessionFactory.getCurrentSession().persist(task);
        Integer savedId = task.getTaskId();
        assertEquals(task.getTitle(),
                sessionFactory.getCurrentSession().get(Task.class, savedId).getTitle());

    }

    @Test
    public void canAddTaskToContest() {
        Contest contest = createContest();
        Task task = createTask();
        contest.getTasks().add(task);
        task.setContest(contest);
        sessionFactory.getCurrentSession().persist(contest);
        Contest persistedContest = sessionFactory.getCurrentSession().get(Contest.class, contest.getContestId());
        Task persistedTask = persistedContest.getTasks().get(0);
        assertEquals(1L, persistedContest.getTasks().size());
        assertEquals(task.getTaskId(), persistedContest.getTasks().get(0).getTaskId());
        assertNotNull(task.getTaskId());
    }

    @Test
    public void canAddAndDeleteTaskFromContest() {
        Contest contest = createContest();
        Task task = createTask();
        contest.getTasks().add(task);
        sessionFactory.getCurrentSession().persist(contest);
        contest.getTasks().remove(task);
        sessionFactory.getCurrentSession().persist(contest);
        Contest persistedContest = sessionFactory.getCurrentSession().get(Contest.class, contest.getContestId());
        assertEquals(0, persistedContest.getTasks().size());
    }

    @Test
    public void canPersistTest() {
        ru.hh.school.checkup.entity.Test test =  createTest();
        sessionFactory.getCurrentSession().persist(test);
    }

    @Test
    public void canPersistTestWithinTask() {
        Task task = createTask();
        ru.hh.school.checkup.entity.Test test =  createTest();
        task.getTests().add(test);
        test.setTask(task);
        sessionFactory.getCurrentSession().persist(task);
        Task persistedTask = sessionFactory.getCurrentSession().get(Task.class, task.getTaskId());
        assertEquals(test, persistedTask.getTests().get(0));
        sessionFactory.getCurrentSession().persist(test);
    }

    Contest createContest() {
        Contest contest = new Contest();
        contest.setName("First");
        contest.setContestStart(new Timestamp(new Date().getTime()));
        contest.setContestEnd(new Timestamp(new Date().getTime()));
        contest.setActive(true);
        return contest;
    }

    Task createTask() {
        Task task = new Task();
        task.setTitle("Task 1");
        task.setTaskText("Crazy task");
        return task;
    }

    ru.hh.school.checkup.entity.Test createTest() {
        ru.hh.school.checkup.entity.Test test = new ru.hh.school.checkup.entity.Test();
        test.setTestInput("1 2");
        test.setExpectedOutput("3");
        return test;
    }
}
