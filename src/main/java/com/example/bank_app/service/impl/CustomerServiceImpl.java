package com.example.bank_app.service.impl;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.entity.Customer;
import com.example.bank_app.mapper.CustomerMapper;
import com.example.bank_app.repositary.CustomerRepositary;
import com.example.bank_app.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepositary repository;

    public CustomerServiceImpl(CustomerRepositary repository) {
        this.repository = repository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Customer saved = repository.save(customer);
        return CustomerMapper.mapToCustomerDto(saved);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPassword(customerDto.password());

        Customer updated = repository.save(customer);
        return CustomerMapper.mapToCustomerDto(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }


}
