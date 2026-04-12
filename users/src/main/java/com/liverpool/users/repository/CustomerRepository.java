package com.liverpool.users.repository;

import com.liverpool.users.domain.document.Customer;
import com.liverpool.users.domain.valueobject.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    boolean existsByEmail(Email email);

}
