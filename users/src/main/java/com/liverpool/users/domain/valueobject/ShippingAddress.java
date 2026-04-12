package com.liverpool.users.domain.valueobject;

import com.liverpool.users.domain.util.DomainValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceCreator;

@Getter
@EqualsAndHashCode
public class ShippingAddress {
    private final String street;
    private final String city;
    private final String neighborhood;
    private final String state;
    private final String zipCode;
    private final String country;

    @PersistenceCreator
    private ShippingAddress(
            String street,
            String city,
            String neighborhood,
            String state,
            String zipCode,
            String country) {
        this.street = street;
        this.city = city;
        this.neighborhood = neighborhood;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
    private ShippingAddress() {
        this.street = null;
        this.city = null;
        this.neighborhood = null;
        this.state = null;
        this.zipCode = null;
        this.country = null;
    }

    public static ShippingAddress of(String street,
                                     String city,
                                     String neighborhood,
                                     String state,
                                     String zipCode,
                                     String country) {

        DomainValidator.validateNotBlank(street, "La calle es requerida");
        DomainValidator.validateNotBlank(city, "La ciudad es requerida");
        DomainValidator.validateNotBlank(state, "El estado es requerido");
        DomainValidator.validateNotBlank(country, "El país es requerido");
        DomainValidator.validatePattern(zipCode, "^\\d{5}$","El código postal debe tener 5 dígitos");
        return new ShippingAddress(street, city, neighborhood, state, zipCode, country);
    }

}
