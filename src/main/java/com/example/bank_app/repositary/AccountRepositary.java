package com.example.bank_app.repositary;

import com.example.bank_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositary extends JpaRepository<Account,Long> {

}
