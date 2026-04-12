package com.liverpool.users.mapper;

import com.liverpool.users.domain.document.Customer;
import com.liverpool.users.domain.valueobject.ShippingAddress;
import com.liverpool.users.dto.request.CustomerRequestDto;
import com.liverpool.users.dto.response.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "email",source = "email.value")
    @Mapping(target = "shippingAddress", source = "shippingAddress")
    CustomerResponseDto toResponseDto(Customer customer);

    @Mapping(target = "street", source = "street")
    @Mapping(target = "neighborhood", source = "neighborhood")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "country", source = "country")
    CustomerResponseDto.ShippingAddressDto toShippingAddressDto(ShippingAddress shippingAddress);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "email",ignore = true)
    @Mapping(target = "shippingAddress", ignore = true)
    @Mapping(target = "createdDate",ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Customer toDocument(CustomerRequestDto customerRequestDto);
}
