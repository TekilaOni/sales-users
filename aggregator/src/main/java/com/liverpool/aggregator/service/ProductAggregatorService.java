package com.liverpool.aggregator.service;

import com.liverpool.aggregator.dto.product.request.ProductRequestDto;
import com.liverpool.aggregator.dto.product.response.ProductResponseDto;

public interface ProductAggregatorService {

    public ProductResponseDto create(ProductRequestDto request);

    public ProductResponseDto findById(String id);

    public ProductResponseDto update(String id, ProductRequestDto request);

    public void delete(String id);
}
