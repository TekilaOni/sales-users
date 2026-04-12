package com.liverpool.users.controller;

import com.liverpool.users.dto.request.CustomerRequestDto;
import com.liverpool.users.dto.response.CustomerResponseDto;
import com.liverpool.users.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
@Tag(name = "Customers", description = "Gestion de clientes")
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busqueda de cliente", description = "Busca cliente por medio de identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public CustomerResponseDto getCustomer(@PathVariable("id") String id) {
        return customerService.findById(id);
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
        return customerService.updateCustomer(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Borrado de cliente", description = "Eliminación de cliente por medio de identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public void deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
    }

}
