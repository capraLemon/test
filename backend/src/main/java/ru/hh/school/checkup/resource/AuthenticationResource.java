package ru.hh.school.checkup.resource;


import ru.hh.school.checkup.dto.AccountDto;
import ru.hh.school.checkup.dto.CredentialsDto;
import ru.hh.school.checkup.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    private AccountService accountService;

    @Inject
    public AuthenticationResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/userData")
    public AccountDto getUserData() {
        return new AccountDto(accountService.getCurrentUserAccount());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public void authenticateUser(CredentialsDto credentials) {
        accountService.login(credentials);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/logout")
    public void logoutUser() {
        accountService.logoutUser();
    }



}
