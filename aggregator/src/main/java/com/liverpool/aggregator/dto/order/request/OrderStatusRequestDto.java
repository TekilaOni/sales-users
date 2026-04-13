package com.liverpool.aggregator.dto.order.request;

import com.liverpool.orders.domain.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequestDto {

    @NotNull(message = "El status es requerido")
    private OrderStatus status;
}