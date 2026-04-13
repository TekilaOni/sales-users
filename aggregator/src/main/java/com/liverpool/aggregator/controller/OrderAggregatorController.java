package com.liverpool.aggregator.controller;

import com.liverpool.aggregator.dto.CreateOrderRequest;
import com.liverpool.aggregator.dto.order.request.OrderRequestDto;
import com.liverpool.aggregator.dto.order.request.OrderStatusRequestDto;
import com.liverpool.aggregator.dto.order.response.OrderResponseDto;
import com.liverpool.aggregator.service.OrderAggregatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
@Tag(name = "Orders", description = "Gestion de Ordenes de venta")

public class OrderAggregatorController {

    private final OrderAggregatorService orderAggregatorService;

    public OrderAggregatorController(OrderAggregatorService orderAggregatorService) {
        this.orderAggregatorService = orderAggregatorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creación de ordenes", description = "Registra una nueva orden de venta en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orden creada de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
    })
    public OrderResponseDto createCustomer(@Valid @RequestBody CreateOrderRequest request) {
        return orderAggregatorService.createOrder(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busqueda de producto", description = "Busca orden por medio de identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrado"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    public OrderResponseDto getOrderById(@PathVariable("id") String id) {
        return orderAggregatorService.findById(id);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualizacion de estatus de orden", description = "Actualiza el estado actual de la orden de venta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualizacion de estatus correcta"),
            @ApiResponse(responseCode = "404", description = "Orden de venta no encontrada"),
            @ApiResponse(responseCode = "422", description = "Cambio de estatus invalido")
    })
    public OrderResponseDto updateStatus(
            @PathVariable String id,
            @Valid @RequestBody OrderStatusRequestDto request) {
        return orderAggregatorService.updateStatus(id, request);
    }

}