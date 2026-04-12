package com.liverpool.products.dto.response;

public record ProductResponseDto (String id, String sku, String name, String description, String category, Boolean price, Integer stock) {
}