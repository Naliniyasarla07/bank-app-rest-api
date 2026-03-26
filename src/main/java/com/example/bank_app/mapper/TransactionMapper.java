package com.example.bank_app.mapper;

import com.example.bank_app.dto.TransactionDto;
import com.example.bank_app.entity.Transaction;

public class TransactionMapper {
    public static TransactionDto toDto(Transaction t) {
        if (t == null) return null;
        return new TransactionDto(
                t.getId(),
                t.getFromAccountId(),
                t.getToAccountId(),
                t.getAmount(),
                t.getType(),
                t.getDate()
        );
    }

    public static Transaction toEntity(TransactionDto dto) {
        if (dto == null) return null;

        Transaction t = new Transaction();
        t.setId(dto.id());
        t.setFromAccountId(dto.fromAccountId());
        t.setToAccountId(dto.toAccountId());
        t.setAmount(dto.amount());
        t.setType(dto.type());
        t.setDate(dto.date());
        return t;
    }
}
