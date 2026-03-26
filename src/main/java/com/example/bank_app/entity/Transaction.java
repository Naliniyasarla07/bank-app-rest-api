package com.example.bank_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transactions")
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private Long fromAccountId;
        private Long toAccountId;
        private double amount;
        private String type; // DEPOSIT, WITHDRAW, TRANSFER
        private LocalDateTime date;
    }


