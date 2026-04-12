package com.liverpool.products.domain.valueobject;

import com.liverpool.products.domain.util.DomainValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceCreator;

@Getter
@EqualsAndHashCode
public class Sku {

    private final String value;

    @PersistenceCreator
    private Sku(String value) {
        this.value = value;
    }

    private Sku() {
        this.value = null;
    }

    public static Sku of(String value) {
        DomainValidator.validateNotBlank(value, "El Sku es obligatorio para el registro");
        DomainValidator.validatePattern(value,"^[A-Z0-9\\-]{6,20}$","El SKU debe tener entre 6 y 20 caracteres en mayúsculas");
        return new Sku(value.toUpperCase().trim());
    }
}
