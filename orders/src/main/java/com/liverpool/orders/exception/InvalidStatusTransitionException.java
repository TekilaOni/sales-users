package com.liverpool.orders.exception;

import com.liverpool.orders.domain.valueobject.OrderStatus;

import java.util.List;
import java.util.Map;

public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(OrderStatus currentStatus, OrderStatus newStatus) {
        super("No es posible cambiar el status de " + currentStatus + " a " + newStatus +
                ". Transiciones permitidas desde " + currentStatus + ": " +
                getAllowedTransitions(currentStatus));
    }

    private static String getAllowedTransitions(OrderStatus currentStatus) {
        Map<OrderStatus, List<OrderStatus>> allowedTransitions = Map.of(
                OrderStatus.PENDING,   List.of(OrderStatus.CONFIRMED, OrderStatus.CANCELLED),
                OrderStatus.CONFIRMED, List.of(OrderStatus.SHIPPED,   OrderStatus.CANCELLED),
                OrderStatus.SHIPPED,   List.of(OrderStatus.DELIVERED),
                OrderStatus.DELIVERED, List.of(),
                OrderStatus.CANCELLED, List.of()
        );
        List<OrderStatus> transitions = allowedTransitions.get(currentStatus);
        return transitions.isEmpty() ? "ninguna, es un estado que no se puede cambiar" : transitions.toString();
    }
}
