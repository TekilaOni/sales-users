package com.liverpool.products.service;

import com.liverpool.products.dto.request.ProductRequestDto;
import com.liverpool.products.dto.response.ProductResponseDto;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto findById(String id);

    ProductResponseDto updateProduct(String id,ProductRequestDto productRequestDto);

    void deleteProduct(String id);
}