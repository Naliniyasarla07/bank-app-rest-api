package com.example.bank_app.mapper;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.entity.Customer;



public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setId(customerDto.id());
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());

        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {

        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                null
        );
    }
}

