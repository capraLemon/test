package ru.hh.school.checkup.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EntityNotFoundException extends WebApplicationException {

    public EntityNotFoundException(String template, Object... args) {
        super(
                String.format(template, args),
                Response.status(Response.Status.NOT_FOUND).build()
        );

    }

    public EntityNotFoundException(String message) {
        super(
                message,
                Response.status(Response.Status.NOT_FOUND).build()
        );
    }
}
