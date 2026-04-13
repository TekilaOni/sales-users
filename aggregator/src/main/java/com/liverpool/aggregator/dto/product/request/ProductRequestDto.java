package com.liverpool.aggregator.dto.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "El Sku no puede estar vacio")
    @Pattern(regexp = "^[A-Z0-9\\-]{6,20}$", message = "El SKU debe tener entre 6 y 20 caracteres en mayúsculas")
    private String sku;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String name;

    @NotBlank(message = "La descripcion del producto no puede estar vacia")
    private String description;

    @NotBlank(message = "La categoria del producto no puede estar vacia")
    private String category;

    @NotNull(message = "El precio no puede estar vacio")
    private BigDecimal amount;

    @NotNull(message = "La cantidad no puede estar vacia")
    private Integer quantity;
}
