package com.example.bank_app.controller;

import com.example.bank_app.dto.TransactionDto;
import com.example.bank_app.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;
    public TransactionController(TransactionService service) {
        this.service = service;
    }
    @PostMapping("/deposit")
    public TransactionDto deposit(@RequestParam Long accountId,
                                  @RequestParam double amount) {
        return service.deposit(accountId, amount);
    }

    @PostMapping("/withdraw")
    public TransactionDto withdraw(@RequestParam Long accountId,
                                   @RequestParam double amount) {
        return service.withdraw(accountId, amount);
    }


    @PostMapping("/transfer")
    public TransactionDto transfer(@RequestBody TransactionDto dto) {
        return service.transfer(dto.fromAccountId(), dto.toAccountId(), dto.amount());
    }

}
