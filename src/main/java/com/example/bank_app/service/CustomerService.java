package com.example.bank_app.service;
import java.util.List;
import com.example.bank_app.dto.CustomerDto;

public interface CustomerService {

CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long id);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);
}
