package com.liverpool.orders.service.impl;

import com.liverpool.orders.domain.document.Order;
import com.liverpool.orders.domain.valueobject.OrderStatus;
import com.liverpool.orders.domain.valueobject.Price;
import com.liverpool.orders.dto.request.OrderItemRequestDto;
import com.liverpool.orders.dto.request.OrderRequestDto;
import com.liverpool.orders.dto.request.OrderStatusRequestDto;
import com.liverpool.orders.dto.response.OrderResponseDto;
import com.liverpool.orders.exception.InvalidStatusTransitionException;
import com.liverpool.orders.exception.ResourceNotFoundException;
import com.liverpool.orders.mapper.OrderMapper;
import com.liverpool.orders.repository.OrderRepository;
import com.liverpool.orders.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class OrderSericeImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderSericeImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto request) {
        BigDecimal total = calculateTotal(request);

        Order order = orderMapper.toDocument(request);
        order.setOrderNumber(generateOrderNumber());
        order.setPrice(Price.of(total));
        order.setStatus(OrderStatus.PENDING);

        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto findByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .map(orderMapper::toResponseDto)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Orden no encontrada con numero: " + orderNumber
                ));
    }

    @Override
    public OrderResponseDto findById(String id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponseDto)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Orden no encontrada con id: " + id
                ));
    }

    @Override
    public OrderResponseDto updateStatus(String id, OrderStatusRequestDto newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Orden no encontrada con id: " + id
                ));
        validateStatusChange(order.getStatus(),newStatus.getStatus());
        order.setStatus(newStatus.getStatus());
        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    private BigDecimal calculateTotal(OrderRequestDto request) {
        return request.getItems().stream()
                .map(OrderItemRequestDto::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String generateOrderNumber() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String sequence = String.format("%05d", orderRepository.count() + 1);
        return "ORD-" + date + "-" + sequence;
    }

    private void validateStatusChange(OrderStatus currentStatus, OrderStatus newStatus) {
        Map<OrderStatus, List<OrderStatus>> allowedChanges = Map.of(
                OrderStatus.PENDING, List.of(OrderStatus.CONFIRMED, OrderStatus.CANCELLED),
                OrderStatus.CONFIRMED, List.of(OrderStatus.SHIPPED, OrderStatus.CANCELLED),
                OrderStatus.SHIPPED, List.of(OrderStatus.DELIVERED),
                OrderStatus.DELIVERED, List.of(),
                OrderStatus.CANCELLED, List.of()
        );
        List<OrderStatus> allowedStatuses = allowedChanges.get(currentStatus);
        if(!allowedStatuses.contains(newStatus)) {
            throw new InvalidStatusTransitionException(currentStatus, newStatus);
        }
    }
}
