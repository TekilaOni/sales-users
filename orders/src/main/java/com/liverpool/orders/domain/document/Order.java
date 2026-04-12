package com.liverpool.orders.domain.document;

import com.liverpool.orders.domain.document.embedded.OrderItem;
import com.liverpool.orders.domain.valueobject.OrderStatus;
import com.liverpool.orders.domain.valueobject.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    @Indexed(unique = true)
    private String orderNumber;

    private String customerId;

    private List<OrderItem> items;

    private Price price;

    private OrderStatus status;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
