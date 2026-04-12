package com.liverpool.orders.repository;

import com.liverpool.orders.domain.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    boolean existsByOrderNumber(String orderId);
    Optional<Order> findByOrderNumber(String orderId);
}
