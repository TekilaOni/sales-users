package com.liverpool.orders.service;

import com.liverpool.orders.dto.request.OrderRequestDto;
import com.liverpool.orders.dto.request.OrderStatusRequestDto;
import com.liverpool.orders.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);
    OrderResponseDto findByOrderNumber(String orderNumber);
    OrderResponseDto findById(String id);
    OrderResponseDto updateStatus(String id, OrderStatusRequestDto newStatus);
}
