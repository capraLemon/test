package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.Account;

import javax.inject.Inject;

@Repository
public class AccountDao extends AbstractDAO<Account, Integer> {

    @Inject
    public AccountDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public Account findUserByName(String login) {
        return sessionFactory.getCurrentSession().createQuery(
                "select acc from Account acc where acc.login =:userLogin", Account.class)
                .setParameter("userLogin", login)
                .uniqueResult();
    }
}
