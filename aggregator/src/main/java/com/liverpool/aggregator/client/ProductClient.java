package com.liverpool.aggregator.client;

import com.liverpool.aggregator.dto.customer.response.CustomerResponseDto;
import com.liverpool.aggregator.dto.product.request.ProductRequestDto;
import com.liverpool.aggregator.dto.product.response.ProductResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

@HttpExchange("/api/products")
public interface ProductClient {

    @PostExchange
    ProductResponseDto create(@RequestBody ProductRequestDto request);

    @GetExchange("/{id}")
    ProductResponseDto get(@PathVariable String id);

    @PutExchange("/{id}")
    ProductResponseDto  update(@PathVariable String id, @RequestBody ProductRequestDto request);

    @DeleteExchange("/{id}")
    void delete(@PathVariable String id);
}
