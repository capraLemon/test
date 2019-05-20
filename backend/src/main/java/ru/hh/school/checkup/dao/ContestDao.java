package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.Contest;

import javax.inject.Inject;


@Repository
public class ContestDao extends AbstractDAO<Contest, Integer> {

    @Inject
    public ContestDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
