package com.liverpool.users.controller;

import com.liverpool.users.dto.request.CustomerRequestDto;
import com.liverpool.users.dto.response.CustomerResponseDto;
import com.liverpool.users.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerRequestDto request) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto getCustomer(@PathVariable("id") String id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto updateCustomer(@PathVariable("id") String id,@Valid @RequestBody CustomerRequestDto request) {
        return customerService.updateCustomer(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
    }

}
