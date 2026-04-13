package com.liverpool.aggregator.service;

import com.liverpool.aggregator.dto.CreateOrderRequest;
import com.liverpool.aggregator.dto.order.request.OrderStatusRequestDto;
import com.liverpool.aggregator.dto.order.response.OrderResponseDto;

public interface OrderAggregatorService {

    public OrderResponseDto createOrder(CreateOrderRequest request);
    public OrderResponseDto findById(String id);
    public OrderResponseDto updateStatus(String id, OrderStatusRequestDto request);

}
