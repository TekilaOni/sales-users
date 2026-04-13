package com.liverpool.aggregator.service.impl;

import com.liverpool.aggregator.client.ProductClient;
import com.liverpool.aggregator.dto.product.request.ProductRequestDto;
import com.liverpool.aggregator.dto.product.response.ProductResponseDto;
import com.liverpool.aggregator.service.ProductAggregatorService;
import org.springframework.stereotype.Service;

@Service
public class ProductAggregatorServiceImpl implements ProductAggregatorService {
    private final ProductClient productClient;

    public ProductAggregatorServiceImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto request) {
        return productClient.create(request);
    }

    @Override
    public ProductResponseDto findById(String id) {
        return productClient.get(id);
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto request) {
        return productClient.update(id, request);
    }

    @Override
    public void delete(String id) {
        productClient.delete(id);
    }

}
