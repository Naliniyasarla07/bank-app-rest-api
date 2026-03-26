package com.example.bank_app.service.impl;


import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repositary.AccountRepositary;
import com.example.bank_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class  AccountServiceImpl implements AccountService {

    private final AccountRepositary accountRepositary;
    public AccountServiceImpl(AccountRepositary accountRepositary)
            {
              this.accountRepositary=accountRepositary;
            }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account= AccountMapper.mapToAccount(accountDto);
         Account savedAccount =accountRepositary.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {


       Account account= accountRepositary
               .findById(id)
               .orElseThrow(()->new RuntimeException("account does not exist"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account= accountRepositary
                .findById(id)
                .orElseThrow(()->new RuntimeException("account does not exist"));
       double total= account.getBalance()+amount;
       account.setBalance(total);
      Account savedAccount= accountRepositary.save(account);

        return AccountMapper.mapToAccountDto(savedAccount) ;
    }

    @Override

    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepositary
                .findById(id)
                .orElseThrow(()->new RuntimeException("account does not exist"));
        if(account.getBalance()<amount)
        {
            throw new RuntimeException("insufficient balance");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount=accountRepositary.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepositary.findAll();
         return accounts.stream().map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        accountRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("account does not exist"));

        accountRepositary.deleteById(id);
    }

}
