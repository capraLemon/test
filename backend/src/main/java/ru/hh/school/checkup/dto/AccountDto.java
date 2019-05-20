package ru.hh.school.checkup.dto;

import ru.hh.school.checkup.entity.Account;

public class AccountDto {
    public Boolean isAuthenticated;
    public String name;

    public AccountDto(Account account) {
        isAuthenticated = true;
        name = account.getUserName();
    }
}
