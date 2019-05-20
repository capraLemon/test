package ru.hh.school.checkup.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.SolutionStatus;
import ru.hh.school.checkup.entity.Task;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


@Repository
public class SolutionDao extends AbstractDAO<Solution, Integer> {

    @Inject
    public SolutionDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public Optional<Solution> getUncheckedSolution() {
        return sessionFactory.getCurrentSession().createQuery(
                "select sol " +
                        "from Solution sol " +
                        "join fetch sol.task t " +
                        "join fetch sol.account " +
                        "where " +
                        "   sol.statusId = :status order by sol.solutionId", Solution.class)
                    .setParameter("status", SolutionStatus.IN_QUEUE)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .uniqueResultOptional();
    }

    public List<Solution> getSolutions(Account account, Task task) {
        return sessionFactory.getCurrentSession().createQuery(
                "select sol from Solution sol where"
                        + " sol.account=:account and sol.task=:task order by sol.solutionId desc")
                    .setParameter("account", account)
                    .setParameter("task", task)
                    .list();
    }
}
