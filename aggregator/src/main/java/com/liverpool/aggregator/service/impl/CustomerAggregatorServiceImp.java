package com.liverpool.aggregator.service.impl;

import com.liverpool.aggregator.client.CustomerClient;
import com.liverpool.aggregator.dto.customer.request.CustomerRequestDto;
import com.liverpool.aggregator.dto.customer.response.CustomerResponseDto;
import com.liverpool.aggregator.service.CustomerAggregatorService;
import org.springframework.stereotype.Service;


@Service
public class CustomerAggregatorServiceImp implements CustomerAggregatorService {

    private final CustomerClient customerClient;

    CustomerAggregatorServiceImp(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public CustomerResponseDto create(CustomerRequestDto request) {
        return customerClient.create(request);
    }

    @Override
    public CustomerResponseDto findById(String id) {
        return customerClient.get(id);
    }

    @Override
    public CustomerResponseDto update(String id, CustomerRequestDto request) {
        return customerClient.update(id, request);
    }

    @Override
    public void delete(String id) {
        customerClient.delete(id);
    }
}
