package com.liverpool.users.domain.document;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "customers")
public class Customer {

    @Id
    private String id;
    @Field("first_name")
    @NotBlank
    private String firstName;
    @Field("last_name")
    @NotBlank
    private String lastName;
    @Field("second_last_name")
    @NotBlank
    private String secondLastName;
}
