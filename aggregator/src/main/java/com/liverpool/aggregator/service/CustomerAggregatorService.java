package com.liverpool.aggregator.service;

import com.liverpool.aggregator.dto.customer.request.CustomerRequestDto;
import com.liverpool.aggregator.dto.customer.response.CustomerResponseDto;

public interface CustomerAggregatorService {

    public CustomerResponseDto create(CustomerRequestDto request);

    public CustomerResponseDto findById(String id);

    public CustomerResponseDto update(String id, CustomerRequestDto request);

    public void delete(String id);
}
