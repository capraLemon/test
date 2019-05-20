package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.checkup.resource.tmp.Todo;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(value = {"createdAt"})
@XmlRootElement
public class TodoDto {

    public String title;
    public Integer id;
    public Boolean completed;

    public TodoDto(Todo todo) {
        title = todo.getTitle();
        id = todo.getId();
        completed = todo.getCompleted();
    }

    public TodoDto() {

    }
}
