package com.liverpool.users.service;

import com.liverpool.users.dto.request.CustomerRequestDto;
import com.liverpool.users.dto.response.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto findById(String id);

    CustomerResponseDto updateCustomer(String id, CustomerRequestDto customerRequestDto);

    void deleteCustomer(String id);
}
