package com.georgik1.productservice.service;

import com.georgik1.productservice.model.dto.ProductRequestDto;
import com.georgik1.productservice.model.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getAllProducts();
}
