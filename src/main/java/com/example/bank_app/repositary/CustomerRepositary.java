package com.example.bank_app.repositary;

import com.example.bank_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositary extends JpaRepository<Customer,Long> {
}
