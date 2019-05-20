package ru.hh.school.checkup.exception;

import ru.hh.school.checkup.dto.ErrorDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CheckupExceptionMapper
        implements ExceptionMapper<CheckupException> {
    @Override
    public Response toResponse(CheckupException ex) {
        ErrorDto errorMessage = new ErrorDto(ex.getMessage());
        return Response
                .status(ex.getResponseStatus())
                .entity(errorMessage)
                .build();
    }
}
