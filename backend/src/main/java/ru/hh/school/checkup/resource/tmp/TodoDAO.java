package ru.hh.school.checkup.resource.tmp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.checkup.dto.TodoDto;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


public class TodoDAO {

    SessionFactory sessionFactory;

    @Inject
    public TodoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Transactional
    public Todo save(TodoDto todoDTO) {
        Todo todo = new Todo();
        todo.setTitle(todoDTO.title);
        todo.setCreatedAt(new Date());
        todo.setCompleted(false);
        sessionFactory.getCurrentSession().persist(todo);
        return todo;
    }

    @Transactional
    public void clearAll() {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Todo")
                .executeUpdate();
    }

    public Todo getById(Integer id) {
        return sessionFactory.getCurrentSession().get(Todo.class, id);
    }

    public Todo deleteById(Integer id) {
        Todo todo = getById(id);
        if (todo == null) {
            return null;
        }
        sessionFactory.getCurrentSession().createQuery("DELETE from Todo WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return todo;
    }

    public List<Todo> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Todo", Todo.class).list();
    }

    @Transactional
    public Todo updateById(Integer id, TodoDto todoDTO) {
        Todo todo = getById(id);
        if (todo == null) {
            return null;
        }
        todo.setTitle(todoDTO.title);
        todo.setCompleted(todoDTO.completed);
        sessionFactory.getCurrentSession().update(todo);
        return todo;
    }
}
