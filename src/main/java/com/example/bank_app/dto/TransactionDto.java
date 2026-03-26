package com.example.bank_app.dto;

import java.time.LocalDateTime;

public record TransactionDto (Long id,
         Long fromAccountId,
         Long toAccountId,
         double amount,
        String type, // DEPOSIT, WITHDRAW, TRANSFER
        LocalDateTime date){
}
