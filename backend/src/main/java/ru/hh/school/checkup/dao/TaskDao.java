package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.Task;

import javax.inject.Inject;


@Repository
public class TaskDao extends AbstractDAO<Task, Integer> {

    @Inject
    public TaskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
