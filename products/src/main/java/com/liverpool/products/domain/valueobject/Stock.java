package com.liverpool.products.domain.valueobject;

import com.liverpool.products.domain.util.DomainValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceCreator;

@Getter
@EqualsAndHashCode
public class Stock {

    private final Integer quantity;

    @PersistenceCreator
    private Stock(Integer quantity) {
        this.quantity = quantity;
    }

    private  Stock() {
        this.quantity = null;
    }

    public static Stock of(Integer quantity) {
        DomainValidator.validateNotNull(quantity,"La cantidad disponible es obligatoria");
        DomainValidator.validateNotNegative(quantity,0,"La cantidad de disponible no debe ser menor que cero");
        return new Stock(quantity);
    }

    public boolean hasStock() {
        return quantity>0;
    }

    public boolean hasEnough(Integer requested) {
        return quantity>=requested;
    }
}