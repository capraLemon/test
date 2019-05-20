package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.BrowserTestCheck;

import javax.inject.Inject;

@Repository
public class BrowserTestCheckDao extends AbstractDAO<BrowserTestCheck, Integer> {

    @Inject
    public BrowserTestCheckDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
