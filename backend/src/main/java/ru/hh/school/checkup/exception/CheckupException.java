package ru.hh.school.checkup.exception;

import javax.ws.rs.core.Response;

public class CheckupException extends RuntimeException {
    public Response.Status responseStatus;
    public CheckupException(Response.Status status, String s) {
        super(s);
        this.responseStatus = status;
    }
    public CheckupException(Response.Status status, String template, Object... args) {
        this(status, String.format(template, args));
    }

    public Response.Status getResponseStatus() {
        return responseStatus;
    }
}
