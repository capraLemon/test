package ru.hh.school.checkup.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.AccountDao;
import ru.hh.school.checkup.dto.CredentialsDto;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.exception.ErrorMessages;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Component
public class AccountService {

    private AccountDao accountDao;
    private Properties serviceProperties;

    private static ThreadLocal<HttpSession> httpSessionThreadLocal = new ThreadLocal<>();

    @Inject
    public AccountService(AccountDao accountDao, Properties serviceProperties) {
        this.accountDao = accountDao;
        this.serviceProperties = serviceProperties;
    }

    public static void setHttpSession(HttpSession session) {
        httpSessionThreadLocal.set(session);
    }

    @Transactional
    public Account getCurrentUserAccount() {
        Integer accountId = (Integer) httpSessionThreadLocal.get().getAttribute("accountId");
        if (accountId == null) {
            throw new CheckupException(Response.Status.UNAUTHORIZED, ErrorMessages.NOT_AUTHENTICATED.getErrorMessage());
        }
        return accountDao.findById(accountId);
    }

    public Account getAdminAccount() {
        //TODO replace with actual code
        Integer FAKE_USER_ADMIN = 1;
        return accountDao.findById(FAKE_USER_ADMIN);
    }

    public Account getAccountById(Integer accountId) {
        return accountDao.findById(accountId);
    }

    @Transactional
    public void login(CredentialsDto credentials) {
        String salt = serviceProperties.getProperty("salt");
        Account account = accountDao.findUserByName(credentials.login);
        if (account != null && hashPassword(salt, credentials.password)
                                    .equals(account.getPassword())) {
            authenticateUser(account);
        } else {
            throw new CheckupException(Response.Status.BAD_REQUEST,
                    ErrorMessages.USER_LOGIN_OR_PASSWORD_IS_INCORRECT.getErrorMessage());
        }
    }

    private void authenticateUser(Account account) {
        httpSessionThreadLocal.get().setAttribute("accountId", account.getAccountId());
    }

    @Transactional
    public void logoutUser() {
        Account currentUserAccount = getCurrentUserAccount();
        httpSessionThreadLocal.get().removeAttribute("accountId");
    }

    public String hashPassword(String salt, String password) {
        return BCrypt.hashpw(password, salt);
    }

}
