package com.liverpool.products.controller;

import com.liverpool.products.dto.request.ProductRequestDto;
import com.liverpool.products.dto.response.ProductResponseDto;
import com.liverpool.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
@Tag(name = "Products", description = "Gestion de products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creación de producto", description = "Registra un nuevo producto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "409", description = "Correo ya registrado")
    })
    public ProductResponseDto createCustomer(@Valid @RequestBody ProductRequestDto request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busqueda de producto", description = "Busca producto por medio de identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ProductResponseDto getCustomer(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualización de producto", description = "Actualiza registro de producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "409", description = "SKU ya registrado (duplicado)")
    })
    public ProductResponseDto updateCustomer(@PathVariable("id") String id,@Valid @RequestBody ProductRequestDto request) {
        return productService.updateProduct(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Borrado de producto", description = "Eliminación de producto por medio de identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public void deleteCustomer(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }

}
