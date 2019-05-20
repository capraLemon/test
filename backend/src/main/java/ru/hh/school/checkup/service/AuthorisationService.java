package ru.hh.school.checkup.service;


import org.springframework.stereotype.Component;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Role;
import ru.hh.school.checkup.entity.Roles;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.exception.ErrorMessages;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
public class AuthorisationService {

    private AccountService accountService;
    private ContestService contestService;


    @Inject
    public AuthorisationService(AccountService accountService, ContestService contestService) {
        this.accountService = accountService;
        this.contestService = contestService;
    }

    @Transactional
    public void verifyHasAuthorisation(Roles role) {
        Account userAccount = accountService.getCurrentUserAccount();
        List<Role> userRoles = userAccount.getRoles();
        if (userRoles.stream().anyMatch(r -> r.getRoleName().equals(Roles.ADMIN))){
            return;
        }
        if (!userRoles.stream().anyMatch(r -> r.getRoleName().equals(role))) {
            throw new CheckupException(Response.Status.FORBIDDEN, ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        if (role.equals(Roles.USER) && contestService.getTimeLeft().timeLeftMilliSecs == 0){
            throw new CheckupException(Response.Status.FORBIDDEN, ErrorMessages.CONTEST_PERIOD_FOR_USER_HAS_FINISHED.getErrorMessage());
        }
    }
}
