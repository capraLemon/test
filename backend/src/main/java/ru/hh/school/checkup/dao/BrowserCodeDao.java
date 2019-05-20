package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.BrowserCode;

import javax.inject.Inject;

@Repository
public class BrowserCodeDao extends AbstractDAO<BrowserCode, Integer> {

    @Inject
    public BrowserCodeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


}
