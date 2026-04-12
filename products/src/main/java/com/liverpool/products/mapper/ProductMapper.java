package com.liverpool.products.mapper;

import com.liverpool.products.domain.document.Product;
import com.liverpool.products.dto.request.ProductRequestDto;
import com.liverpool.products.dto.response.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "sku", source = "sku.value")
    @Mapping(target = "price", source = "price.amount")
    @Mapping(target = "stock", source = "stock.quantity")
    ProductResponseDto toResponseDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "createdDate",ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Product toDocument(ProductRequestDto productRequestDto);
}