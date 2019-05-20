package ru.hh.school.checkup.filter;

import ru.hh.school.checkup.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {
    @Context
    HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        AccountService.setHttpSession(request.getSession());
    }
}
