package com.liverpool.users.domain.document;

import com.liverpool.users.domain.valueobject.Email;
import com.liverpool.users.domain.valueobject.ShippingAddress;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "customers")
@Builder
@Data
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

    private Email email;

    private ShippingAddress shippingAddress;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
