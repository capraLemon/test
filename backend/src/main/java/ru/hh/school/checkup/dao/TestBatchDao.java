package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.TestBatch;

import javax.inject.Inject;

@Repository
public class TestBatchDao extends AbstractDAO<TestBatch, Integer> {

    @Inject
    public TestBatchDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
