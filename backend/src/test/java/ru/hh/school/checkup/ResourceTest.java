package ru.hh.school.checkup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ru.hh.school.checkup.dto.TodoDto;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.resource.tmp.TodoDAO;
import ru.hh.school.checkup.resource.tmp.Todo;
import ru.hh.school.checkup.resource.tmp.TodoService;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.checkup.resource.tmp.TodoResource;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@ContextConfiguration(classes = TestConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class ResourceTest extends NabTestBase {

  private Todo testTodo;

  @Inject
  TodoDAO todoDAO;

  @Inject
  TodoService todoService;


  @Before
  public void setUp() {
    todoDAO.clearAll();
    testTodo = new Todo();
    testTodo.setTitle("some_message");
  }

  @Test
  public void resource() {
    Response response = createRequest("/api/todo/list").get();
    System.out.println(response.readEntity(String.class));
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void resourcePost() {
    Todo testTodo = new Todo();
    testTodo.setTitle("differentMessage");
    Response responsePost = target("/api/todo/create")
            .request()
            .buildPost(Entity.json(new TodoDto(testTodo)))
            .invoke();
    Response response = createRequest("/api/todo/list").get();
    assertThat(response.readEntity(String.class), containsString("differentMessage"));
  }

  @Test
  public void resourceGetSingleTodo() {
    Todo newTodo = todoDAO.save(new TodoDto(testTodo));
    Response response = createRequest("/api/todo/" + newTodo.getId()).get();
    assertThat(response.readEntity(String.class), containsString("some_message"));
  }

  @Test
  public void resourceModifySingle() {
    Todo newTodo = todoDAO.save(new TodoDto(testTodo));
    TodoDto dto = new TodoDto(newTodo);
    dto.title = "modified";
    Todo modifiedTodo = todoDAO.updateById(newTodo.getId(), dto);
    Response responsePUT =  target("/api/todo/" + modifiedTodo.getId())
            .request()
            .buildPut(Entity.json(dto))
            .invoke();
    Response response =  createRequest("/api/todo/" + modifiedTodo.getId()).get();
    assertThat(response.readEntity(String.class), containsString("modified"));
  }

  @Test
  public void deleteTodo() {
    Response response =  target("/api/todo/" + testTodo.getId())
            .request()
            .delete();
    assertEquals(todoService.findAll().toString(), "[]");
  }

  @Test (expected = CheckupException.class)
  public void throwExceptionWhenIncorrectIdPassed() {
    todoService.findById(345345);
  }


  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().registerResources(TodoResource.class).bindToRoot().build();
  }
}
