package com.liverpool.orders.mapper;

import com.liverpool.orders.domain.document.Order;
import com.liverpool.orders.domain.document.embedded.OrderItem;
import com.liverpool.orders.dto.request.OrderItemRequestDto;
import com.liverpool.orders.dto.request.OrderRequestDto;
import com.liverpool.orders.dto.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "total", source = "price.amount")
    @Mapping(target = "status", expression = "java(order.getStatus().name())")
    @Mapping(target = "items", source = "items")
    OrderResponseDto toResponseDto(Order order);

    OrderResponseDto.OrderItemResponseDto toItemResponseDto(OrderItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderNumber", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Order toDocument(OrderRequestDto request);

    OrderItem toOrderItem(OrderItemRequestDto itemRequestDto);
}
