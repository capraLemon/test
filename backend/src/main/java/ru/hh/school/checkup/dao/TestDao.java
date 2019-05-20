package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Task;
import ru.hh.school.checkup.entity.Test;

import javax.inject.Inject;
import java.util.List;

@Repository
public class TestDao extends AbstractDAO<Test, Integer> {

    @Inject
    public TestDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Test> findSmokeTestsForTask(Account userAccount, Account admin, Task task) {
        return sessionFactory.getCurrentSession().createQuery(
                "select t from Test t " +
                        "where t.task=:task " +
                        "and t.isSmoke=true " +
                        "and t.author in (:userList) " +
                        "order by t.testId")
                .setParameter("task", task)
                .setParameterList("userList", List.of(userAccount, admin))
                .list();
    }

}
