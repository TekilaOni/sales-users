package com.liverpool.aggregator.dto.customer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String firstName;

    @NotBlank(message = "el primer apellido no puede estar vacio")
    private String lastName;

    private String secondLastName;

    @NotBlank
    private String email;

    @NotBlank(message = "La calle no puede estar vacia")
    private String street;

    private String neighborhood;

    @NotBlank(message = "La ciudad no puede estar vacia")
    private String city;

    @NotBlank(message = "El estado no puede estar vacio")
    private String state;

    @NotBlank(message = "El código postal no puede estar vacio")
    @Pattern(regexp = "^\\d{5}$", message = "El código postal debe tener 5 dígitos")
    private String zipCode;

    @NotBlank(message = "El país no puede estar vacio")
    private String country;
}
