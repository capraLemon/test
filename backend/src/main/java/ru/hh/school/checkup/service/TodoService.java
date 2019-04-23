package ru.hh.school.checkup.service;

import ru.hh.school.checkup.dao.TodoDAO;
import ru.hh.school.checkup.dto.TodoDTO;
import ru.hh.school.checkup.entity.Todo;
import ru.hh.school.checkup.exception.EntityNotFoundException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class TodoService {


    private TodoDAO todoDAO;

    @Inject
    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    @Transactional
    public List<TodoDTO> findAll() {
        return todoDAO.getAll()
                    .stream()
                    .map(todo -> new TodoDTO(todo))
                    .collect(Collectors.toList());
    }

    @Transactional
    public void save(TodoDTO todoDTO) {
        todoDAO.save(todoDTO);
    }

    @Transactional
    public TodoDTO findById(Integer id) {
        Todo todo = todoDAO.getById(id);
        if (todo == null) {
            throw new EntityNotFoundException("Todo with id = %s does not exists", id);
        } else {
            TodoDTO todoDTO = new TodoDTO(todo);
            return todoDTO;
        }
    }

    @Transactional
    public void update(Integer id, TodoDTO todoDTO) {
        Todo updatedTodo = todoDAO.updateById(id, todoDTO);
        if (updatedTodo == null) {
            throw new EntityNotFoundException("Todo with id = %s does not exists", id);
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        Todo todo = todoDAO.deleteById(id);
        if (todo == null) {
            throw new EntityNotFoundException("Todo with id = %s does not exists", id);
        }
    }

}
