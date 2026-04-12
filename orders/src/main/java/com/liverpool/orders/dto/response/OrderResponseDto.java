package com.liverpool.orders.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDto(String id, String orderNumber, String customerId, List<OrderItemResponseDto> items, BigDecimal total, String status) {
    public record OrderItemResponseDto(
            String productId,
            String sku,
            String productName,
            Integer quantity,
            BigDecimal unitPrice,
            BigDecimal subTotal
    ) {}
}
