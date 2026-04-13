package com.liverpool.aggregator.service.impl;

import com.liverpool.aggregator.client.CustomerClient;
import com.liverpool.aggregator.client.OrderClient;
import com.liverpool.aggregator.client.ProductClient;
import com.liverpool.aggregator.dto.CreateOrderRequest;
import com.liverpool.aggregator.dto.customer.response.CustomerResponseDto;
import com.liverpool.aggregator.dto.order.request.OrderItemRequestDto;
import com.liverpool.aggregator.dto.order.request.OrderRequestDto;
import com.liverpool.aggregator.dto.order.request.OrderStatusRequestDto;
import com.liverpool.aggregator.dto.order.response.OrderResponseDto;
import com.liverpool.aggregator.dto.product.response.ProductResponseDto;
import com.liverpool.aggregator.service.CustomerAggregatorService;
import com.liverpool.aggregator.service.OrderAggregatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderAggregatorServiceImpl implements OrderAggregatorService {

    private final CustomerAggregatorService customerAggregatorService;
    private final ProductClient productClient;
    private final OrderClient orderClient;

    public OrderAggregatorServiceImpl(CustomerAggregatorService customerAggregatorService, ProductClient productClient, OrderClient orderClient) {
        this.customerAggregatorService = customerAggregatorService;
        this.productClient = productClient;
        this.orderClient = orderClient;
    }

    @Override
    public OrderResponseDto createOrder(CreateOrderRequest request) {

        CustomerResponseDto customer = customerAggregatorService.findById(request.getCustomerId());
        List<OrderItemRequestDto> validatedItems = request.getItems().stream().map(
                item -> {
                    ProductResponseDto product = productClient.get(item.getProductId());
                    if (product.stock() < item.getQuantity()) {
                        throw new IllegalArgumentException(
                                "Stock insuficiente para: " + product.name() +
                                        " -Disponible: [" + product.stock() +
                                        "] -Solicitado: [" + item.getQuantity() +"]"
                        );
                    }
                    BigDecimal subTotal = product.price().multiply(new BigDecimal(item.getQuantity()))
                            .setScale(2, RoundingMode.HALF_UP);
                    return OrderItemRequestDto.builder()
                            .productId(product.id())
                            .sku(product.sku())
                            .productName(product.name())
                            .quantity(item.getQuantity())
                            .unitPrice(product.price())
                            .subTotal(subTotal)
                            .build();
                }
        ).toList();
        OrderRequestDto orderRequest = OrderRequestDto.builder()
                .customerId(customer.id())
                .items(validatedItems)
                .build();
        return orderClient.create(orderRequest);
    }

    @Override
    public OrderResponseDto findById(String id) {
        return orderClient.getOrderById(id);
    }

    @Override
    public OrderResponseDto updateStatus(String id, OrderStatusRequestDto request) {
        return orderClient.updateStatus(id, request);
    }

}
