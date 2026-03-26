package com.example.bank_app.service;

import com.example.bank_app.dto.TransactionDto;

public interface TransactionService {
    TransactionDto deposit(Long accountId, double amount);

    TransactionDto withdraw(Long accountId, double amount);

    TransactionDto transfer(Long fromId, Long toId, double amount);
}
