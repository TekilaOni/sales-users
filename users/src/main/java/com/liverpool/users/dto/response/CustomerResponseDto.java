package com.liverpool.users.dto.response;

public record CustomerResponseDto(String id, String firstName, String lastName, String secondLastName, String email, ShippingAddressDto shippingAddress) {
    public record ShippingAddressDto(
            String street,
            String neighborhood,
            String city,
            String state,
            String zipCode,
            String country
    ) {}
}