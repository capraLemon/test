package ru.hh.school.checkup.resource;

import ru.hh.school.checkup.dto.TodoDTO;
import ru.hh.school.checkup.service.TodoService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    TodoService todoService;

    @Inject
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    @Path("/list")
    public List<TodoDTO> getAllTodos() {
        return todoService.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public void createTodo(TodoDTO todo) {
       todoService.save(todo);
    }

    @GET
    @Path("/{id}")
    public TodoDTO getTodoById(@PathParam("id") Integer id) {
        return todoService.findById(id);
    }


    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/{id}")
    public void updateTodo(@PathParam("id") Integer id, TodoDTO todo) {
        todoService.update(id, todo);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTodo(@PathParam("id") Integer id) {
        todoService.deleteById(id);
    }
}
