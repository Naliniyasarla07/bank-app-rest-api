package com.example.bank_app.mapper;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.entity.Customer;

public class CustomerMapper {
        public static Customer mapToCustomer(CustomerDto customerDto) {
            return new Customer(
            customerDto.id(),
            customerDto.name(),
            customerDto.email(),
                    customerDto.password()
            );
     }

     public static CustomerDto mapToCustomerDto(Customer customer)
     {
         return new CustomerDto(
                 customer.getId(),
                 customer.getName(),
                 customer.getEmail(),
                 null
         );
     }
}
