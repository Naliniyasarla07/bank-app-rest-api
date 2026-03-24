package com.example.bank_app.dto;


import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private String accountHoldername;
    private  double balance;
}
