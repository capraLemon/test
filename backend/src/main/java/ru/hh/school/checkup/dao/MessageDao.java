package ru.hh.school.checkup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Contest;
import ru.hh.school.checkup.entity.Message;
import ru.hh.school.checkup.entity.Task;

import javax.inject.Inject;
import java.util.List;


@Repository
public class MessageDao extends AbstractDAO<Message, Integer> {

    @Inject
    public MessageDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public Long getUnreadMessagesCountForAccount(Account account) {
        return sessionFactory.getCurrentSession().createQuery(
                "select count(*) from Message m " +
                        "where m.receiverAccount=:receiver and m.isWatched=:is_watched", Long.class)
                .setParameter("receiver", account)
                .setParameter("is_watched", false)
                .uniqueResult();
    }

    public List<Message> getAllMessagesForTask(Account account, Task task) {
        return sessionFactory.getCurrentSession().createQuery(
                "select m from Message m where " +
                        "(m.receiverAccount=:account or m.senderAccount=:account) " +
                        "and m.task=:task order by m.messageId")
                .setParameter("account", account)
                .setParameter("task", task)
                .list();
    }

    public void markMessagesAsWatched(Account account, Task task) {
        sessionFactory.getCurrentSession().createQuery(
                "update Message m set m.isWatched = true " +
                        "where m.receiverAccount=:receiver and m.task=:task")
                .setParameter("receiver", account)
                .setParameter("task", task)
                .executeUpdate();
    }


    public List<Object[]> unreadMsgsQuantityForEachTopic(Account account, Contest contest) {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "with msg as (select task_id from message where receiver_id = ? and is_watched = false),"
            + " tsk as (select task_id, title from task inner join contest using(contest_id) where contest_id = ?)"
            + " select tsk.task_id, tsk.title, count(msg.task_id) from msg right join tsk using(task_id)"
            + " group by (task_id, tsk.title) order by task_id";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter(1, account.getAccountId());
        query.setParameter(2, contest.getContestId());
        List<Object[]> resultList =  query.list();
        return resultList;
    }



    public void markMessagesAsUnWatched(Account account, Task task) {
        sessionFactory.getCurrentSession().createQuery(
                "update Message m set m.isWatched = false " +
                        "where m.receiverAccount=:receiver and m.task=:task")
                .setParameter("receiver", account)
                .setParameter("task", task)
                .executeUpdate();
    }
}
