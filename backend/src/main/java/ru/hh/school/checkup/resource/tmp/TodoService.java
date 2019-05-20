package ru.hh.school.checkup.resource.tmp;

import ru.hh.school.checkup.dto.TodoDto;
import ru.hh.school.checkup.exception.CheckupException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class TodoService {


    private TodoDAO todoDAO;

    @Inject
    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    @Transactional
    public List<TodoDto> findAll() {
        return todoDAO.getAll()
                    .stream()
                    .map(todo -> new TodoDto(todo))
                    .collect(Collectors.toList());
    }

    @Transactional
    public void save(TodoDto todoDTO) {
        todoDAO.save(todoDTO);
    }

    @Transactional
    public TodoDto findById(Integer id) {
        Todo todo = todoDAO.getById(id);
        if (todo == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, "Todo with id = %s does not exists", id);
        } else {
            TodoDto todoDTO = new TodoDto(todo);
            return todoDTO;
        }
    }

    @Transactional
    public void update(Integer id, TodoDto todoDTO) {
        Todo updatedTodo = todoDAO.updateById(id, todoDTO);
        if (updatedTodo == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, "Todo with id = %s does not exists", id);
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        Todo todo = todoDAO.deleteById(id);
        if (todo == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, "Todo with id = %s does not exists", id);
        }
    }

}
