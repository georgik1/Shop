package com.georgik1.productservice.service.impl;

import com.georgik1.productservice.model.dto.ProductRequestDto;
import com.georgik1.productservice.model.dto.ProductResponseDto;
import com.georgik1.productservice.model.Product;
import com.georgik1.productservice.repository.ProductRepository;
import com.georgik1.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    //could have done this ↓ using '@RequiredArgsConstructor' but I like it better that way
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product {} is saved", product.getId());
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
       List<Product> products = productRepository.findAll();

      return products.stream()
               .map(product -> mapToProductResponse(product))
               .collect(Collectors.toList());
    }

    private ProductResponseDto mapToProductResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
