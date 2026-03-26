package com.example.bank_app.service.impl;

import com.example.bank_app.dto.TransactionDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.mapper.TransactionMapper;
import com.example.bank_app.repositary.TransactionRepositary;
import com.example.bank_app.service.TransactionService;
import com.example.bank_app.repositary.AccountRepositary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepositary accountRepo;
    private final TransactionRepositary transactionRepo;

    public TransactionServiceImpl(AccountRepositary accountRepo,
                                  TransactionRepositary transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public TransactionDto deposit(Long accountId, double amount) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        acc.setBalance(acc.getBalance() + amount);
        accountRepo.save(acc);

        Transaction t = new Transaction();
        t.setToAccountId(accountId);
        t.setAmount(amount);
        t.setType("DEPOSIT");
        t.setDate(LocalDateTime.now());
        return TransactionMapper.mapToTransactionDto(transactionRepo.save(t));
    }

    @Override
    public TransactionDto withdraw(Long accountId, double amount) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);
        accountRepo.save(acc);

        Transaction t = new Transaction();
        t.setFromAccountId(accountId);
        t.setAmount(amount);
        t.setType("WITHDRAW");
        t.setDate(LocalDateTime.now());

        return TransactionMapper.mapToTransactionDto(transactionRepo.save(t));
    }

    @Override
    public TransactionDto transfer(Long fromId, Long toId, double amount) {
        // 1️⃣ Get accounts
        Account from = accountRepo.findById(fromId)
                .orElseThrow(() -> new RuntimeException("From account not found"));

        Account to = accountRepo.findById(toId)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        // 2️⃣ Check balance
        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // 3️⃣ Update balances
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepo.save(from);
        accountRepo.save(to);

        // 4️⃣ Create TransactionDto
        TransactionDto dto = new TransactionDto(
                null,
                fromId,
                toId,
                amount,
                "TRANSFER",
                LocalDateTime.now()
        );

        // 5️⃣ Map DTO → Transaction entity using mapper
        Transaction transaction = TransactionMapper.mapToTransaction(dto);

        // 6️⃣ Save transaction
        Transaction saved = transactionRepo.save(transaction);

        // 7️⃣ Return DTO using mapper
        return TransactionMapper.mapToTransactionDto(saved);
    }


}
