package ru.hh.school.checkup.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractDAO<T, ID extends Serializable> {

    protected SessionFactory sessionFactory;
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<T>)((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public T findById(ID id) {
        return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
    }

    public List<T> findAll() {
        return this.findByCriteria();
    }


    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion){
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(persistentClass);

        for(Criterion c: criterion){
            crit.add(c);
        }
        return (List<T>) crit.list();

    }
    @Transactional
    public T save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public List<T> findByCriterias(Map<String, Object> attributes) {
        Session session = sessionFactory.getCurrentSession();
        List<T> results;
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(persistentClass);
        Root<T> foo = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<Predicate>();
        for(String s : attributes.keySet()) {
            if(foo.get(s) != null){
                predicates.add(cb.equal(foo.get(s), attributes.get(s)));
            }
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<T> q = session.createQuery(cq);

        results = q.getResultList();
        return results;
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }


    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public void clear() {
        sessionFactory.getCurrentSession().clear();
    }

}
