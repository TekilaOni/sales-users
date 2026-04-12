package com.liverpool.products.service.impl;

import com.liverpool.products.domain.document.Product;
import com.liverpool.products.domain.valueobject.Price;
import com.liverpool.products.domain.valueobject.Sku;
import com.liverpool.products.domain.valueobject.Stock;
import com.liverpool.products.dto.request.ProductRequestDto;
import com.liverpool.products.dto.response.ProductResponseDto;
import com.liverpool.products.exception.DuplicateResourceException;
import com.liverpool.products.exception.ResourceNotFoundException;
import com.liverpool.products.mapper.ProductMapper;
import com.liverpool.products.repository.ProductRepository;
import com.liverpool.products.service.ProductService;
import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto request) {
        Sku sku = Sku.of(request.getSku());

        if(productRepository.existsBySku(sku)){
            throw new DuplicateResourceException("Ya existe un producto con el mismo SKU"+request.getSku());
        }

        Price price = Price.of(request.getAmount());
        Stock stock = Stock.of(request.getQuantity());

        Product product = Product.builder()
                .sku(sku)
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(price)
                .stock(stock)
                .build();
        return productMapper.toResponseDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto findById(String id) {
        return productRepository.findById(id).map(productMapper::toResponseDto)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Producto no encontrado con id: "+id));
    }

    @Override
    public ProductResponseDto updateProduct(String id,ProductRequestDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Producto no encontrado con id: "+id));
        Sku newSku = Sku.of(request.getSku());
        boolean skuChange = !product.getSku().equals(newSku);
        if(skuChange && productRepository.existsBySku(newSku)){
            throw new DuplicateResourceException("Ya existe un producto con el mismo SKU"+request.getSku());
        }

        Price newPrice = Price.of(request.getAmount());
        Stock newStock = Stock.of(request.getQuantity());
        product.setSku(newSku);
        product.setPrice(newPrice);
        product.setStock(newStock);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        return productMapper.toResponseDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(String id) {
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Producto no encontrado con id: "+id);
        }
        productRepository.deleteById(id);
    }
}
