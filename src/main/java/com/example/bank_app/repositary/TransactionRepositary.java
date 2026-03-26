package com.example.bank_app.repositary;

import com.example.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositary extends JpaRepository<Transaction,Long> {
}
