package com.liverpool.aggregator.dto.order.request;

import jakarta.validation.constraints.Min;
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
public class OrderItemRequestDto {

    @NotBlank(message = "El id del producto no puede estar vacio")
    private String productId;

    @NotBlank(message = "El Sku no puede estar vacio")
    @Pattern(regexp = "^[A-Z0-9\\-]{6,20}$", message = "El SKU debe tener entre 6 y 20 caracteres en mayúsculas")
    private String sku;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String productName;

    @NotNull(message = "La cantidad no puede estar vacia")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private Integer quantity;

    @NotNull(message = "El precio unitario no puede estar vacio")
    private BigDecimal unitPrice;

    @NotNull(message = "El subtotal no puede estar vacio")
    private BigDecimal subTotal;
}
