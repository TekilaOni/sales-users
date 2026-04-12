package com.liverpool.products.domain.document;

import com.liverpool.products.domain.valueobject.Price;
import com.liverpool.products.domain.valueobject.Sku;
import com.liverpool.products.domain.valueobject.Stock;
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

@Document(collection = "products")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;

    @Indexed(unique = true)
    private Sku sku;

    private String name;

    private String description;

    private String category;

    private Price price;

    private Stock stock;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
