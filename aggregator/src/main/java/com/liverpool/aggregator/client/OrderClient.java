package com.liverpool.aggregator.client;

import com.liverpool.aggregator.dto.order.request.OrderRequestDto;
import com.liverpool.aggregator.dto.order.request.OrderStatusRequestDto;
import com.liverpool.aggregator.dto.order.response.OrderResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/orders")
public interface OrderClient {

    @PostExchange
    OrderResponseDto create(@RequestBody OrderRequestDto request);

    @GetMapping("/orderNumber/{orderNumber}")
    OrderResponseDto getOrderByOrderNumber(@PathVariable String orderNumber);

    @GetMapping("/{id}")
    OrderResponseDto getOrderById(@PathVariable String id);

    @PatchExchange("/{id}/status")
    OrderResponseDto updateStatus(@PathVariable String id, @RequestBody OrderStatusRequestDto request);
}
