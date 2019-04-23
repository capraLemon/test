package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.checkup.entity.Todo;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(value = {"createdAt"})
@XmlRootElement
public class TodoDTO {

    public String title;
    public Integer id;
    public Boolean completed;

    public TodoDTO(Todo todo) {
        title = todo.getTitle();
        id = todo.getId();
        completed = todo.getCompleted();
    }

    public TodoDTO() {

    }
}
