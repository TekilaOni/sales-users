package com.liverpool.aggregator.client;

import com.liverpool.aggregator.dto.customer.request.CustomerRequestDto;
import com.liverpool.aggregator.dto.customer.response.CustomerResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

@HttpExchange("/api/customers")
public interface CustomerClient {

    @PostExchange
    CustomerResponseDto create(@RequestBody CustomerRequestDto request);

    @GetExchange("/{id}")
    CustomerResponseDto get(@PathVariable String id);

    @PutExchange("/{id}")
    CustomerResponseDto  update(@PathVariable String id, @RequestBody CustomerRequestDto request);

    @DeleteExchange("/{id}")
    void delete(@PathVariable String id);
}
