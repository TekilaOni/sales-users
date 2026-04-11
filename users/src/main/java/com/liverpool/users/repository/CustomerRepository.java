package com.liverpool.users.repository;

import com.liverpool.users.domain.document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
