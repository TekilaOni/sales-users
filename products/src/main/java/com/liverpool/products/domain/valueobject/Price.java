package com.liverpool.products.domain.valueobject;

import com.liverpool.products.domain.util.DomainValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceCreator;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@EqualsAndHashCode
public class Price {

    private final BigDecimal amount;

    @PersistenceCreator
    private Price(BigDecimal amount) {
        this.amount = amount;
    }

    private Price(){
        this.amount = null;
    }

    public Price of(BigDecimal amount) {
        DomainValidator.validateNotNull(amount,"El precio es obligatorio");
        DomainValidator.validateNotNegative(amount,BigDecimal.ZERO,"El precio no puede ser negativo");
        return new Price(amount);
    }

    public Price multiply(Integer quantity) {
        return new Price(
                this.amount.multiply(BigDecimal.valueOf(quantity))
                        .setScale(2, RoundingMode.HALF_UP)
        );
    }
}
