package com.liverpool.products.repository;

import com.liverpool.products.domain.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsBySku(String sku);

    Optional<Product> findBySku(String sku);
}
