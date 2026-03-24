package com.example.bank_app.service.impl;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.repositary.AccountRepositary;
import com.example.bank_app.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public  class AccountServiceImpl implements AccountService {

    private AccountRepositary accountRepositary;

    public AccountServiceImpl(AccountRepositary accountRepositary) {
        this.accountRepositary = accountRepositary;
    }




    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        return null;
    }
}
