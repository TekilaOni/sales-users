package com.liverpool.users.dto.response;

import com.liverpool.users.domain.valueobject.ShippingAddress;

public record CustomerResponseDto(String id, String firstName, String lastName, String email, ShippingAddressDto shippingAddressDto) {
    public record ShippingAddressDto(
            String street,
            String neighborhood,
            String city,
            String state,
            String zipCode,
            String country
    ) {}
}
