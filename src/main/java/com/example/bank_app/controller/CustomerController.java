package com.example.bank_app.controller;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service) {
        this.service = service;
    }
    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return service.createCustomer(customerDto);
    }
    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        return service.getAllCustomers();
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id,
                                      @RequestBody CustomerDto dto) {
        return service.updateCustomer(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}
