package com.liverpool.aggregator.dto.product.response;

import java.math.BigDecimal;

public record ProductResponseDto(String id, String sku, String name, String description, String category, BigDecimal price, Integer stock) {
}