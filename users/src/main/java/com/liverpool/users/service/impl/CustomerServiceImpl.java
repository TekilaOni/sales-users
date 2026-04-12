package com.liverpool.users.service.impl;

import com.liverpool.users.domain.document.Customer;
import com.liverpool.users.domain.valueobject.Email;
import com.liverpool.users.domain.valueobject.ShippingAddress;
import com.liverpool.users.dto.request.CustomerRequestDto;
import com.liverpool.users.dto.response.CustomerResponseDto;
import com.liverpool.users.exception.DuplicateResourceException;
import com.liverpool.users.exception.ResourceNotFoundException;
import com.liverpool.users.mapper.CustomerMapper;
import com.liverpool.users.repository.CustomerRepository;
import com.liverpool.users.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto request){
        Email email = Email.of(request.getEmail());

        if(customerRepository.existsByEmail(email)){
            throw new DuplicateResourceException("Ya existe un cliente con el mismo correo electronico: "+request.getEmail());
        }
        ShippingAddress address = ShippingAddress.of(
                request.getStreet(),
                request.getCity(),
                request.getNeighborhood(),
                request.getState(),
                request.getZipCode(),
                request.getCountry()
        );
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .email(email)
                .shippingAddress(address)
                .build();

        return customerMapper.toResponseDto(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDto findById(String id) {
        return customerRepository.findById(id).map(customerMapper::toResponseDto)
                .orElseThrow(()->new ResourceNotFoundException("Cliente no encontrado con id: "+id));
    }

    @Override
    public CustomerResponseDto updateCustomer(String id, CustomerRequestDto request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cliente no encontrado con id: "+id));
        Email newEmail = Email.of(request.getEmail());
        boolean emailChange = !customer.getEmail().equals(newEmail);
        if(emailChange && customerRepository.existsByEmail(newEmail)){
            throw new DuplicateResourceException("Ya existe un cliente con el mismo correo electronico: "+request.getEmail());
        }
        ShippingAddress newAddress = ShippingAddress.of(
                request.getStreet(),
                request.getCity(),
                request.getNeighborhood(),
                request.getState(),
                request.getZipCode(),
                request.getCountry()
        );
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setSecondLastName(request.getSecondLastName());
        customer.setEmail(newEmail);
        customer.setShippingAddress(newAddress);
        return customerMapper.toResponseDto(customerRepository.save(customer));
    }
}
