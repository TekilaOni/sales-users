package com.liverpool.aggregator.controller;

import com.liverpool.aggregator.dto.customer.request.CustomerRequestDto;
import com.liverpool.aggregator.dto.customer.response.CustomerResponseDto;
import com.liverpool.aggregator.service.CustomerAggregatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customers", description = "Gestión de clientes desde aggregator")
public class CustomerAggregatorController {

    private final CustomerAggregatorService customerAggregatorService;

    public CustomerAggregatorController(CustomerAggregatorService customerAggregatorService) {
        this.customerAggregatorService = customerAggregatorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creación de cliente", description = "Registra un nuevo cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "409", description = "Correo ya registrado")
    })
    public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerRequestDto request) {
        return customerAggregatorService.create(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busqueda de cliente", description = "Busca cliente por medio de identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public CustomerResponseDto getCustomer(@PathVariable("id") String id) {
        return customerAggregatorService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualización de cliente", description = "Actualiza registro de cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "409", description = "Correo ya registrado (duplicado)")
    })
    public CustomerResponseDto updateCustomer(@PathVariable("id") String id,@Valid @RequestBody CustomerRequestDto request) {
        return customerAggregatorService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Borrado de cliente", description = "Eliminación de cliente por medio de identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public void deleteCustomer(@PathVariable("id") String id) {
        customerAggregatorService.delete(id);
    }
}
