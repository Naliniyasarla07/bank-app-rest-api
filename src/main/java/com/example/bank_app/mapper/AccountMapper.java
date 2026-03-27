package com.example.bank_app.mapper;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Customer;
import jakarta.persistence.*;



public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {

        Account account = new Account();

        account.setId(accountDto.id());
        account.setAccountHoldername(accountDto.accountHolderName());
        account.setBalance(accountDto.balance());

        // 🔥 handle relationship (VERY IMPORTANT)
        Customer customer = new Customer();
        customer.setId(accountDto.customerId()); // DTO must have this

        account.setCustomer(customer);

        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {

        Long customerId = null;

        if (account.getCustomer() != null) {
            customerId = account.getCustomer().getId();
        }

        return new AccountDto(
                account.getId(),
                account.getAccountHoldername(),
                account.getBalance(),
                customerId
        );
    }
}
