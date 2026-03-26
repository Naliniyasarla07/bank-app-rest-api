package com.example.bank_app.controller;

import  com.example.bank_app.dto.AccountDto;
import com.example.bank_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  //  private HttpStatusCode HttpServer;
  private final AccountService accountService;
    //private HttpStatusCode HttpServer;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    //add account restspi
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);

    }
    //getting account id
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById( @PathVariable Long id)
    {
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }
//deposi amount
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request)
    {
        double amount=request.get("amount");
      AccountDto accountDTo =accountService.deposit(id,amount);
      return ResponseEntity.ok(accountDTo);
    }
    //withdraw amount
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request)
    {
        double amount=request.get("amount");
        AccountDto accountDTo =accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDTo);
    }
    // getting all accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts=accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

}
