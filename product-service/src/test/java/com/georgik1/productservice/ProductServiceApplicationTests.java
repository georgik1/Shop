package com.georgik1.productservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgik1.productservice.model.dto.ProductRequestDto;
import com.georgik1.productservice.model.dto.ProductResponseDto;
import com.georgik1.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequestDto productRequestDto = getProductRequest();

        String productRequestString = objectMapper.writeValueAsString(productRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());

    }

    @Test
    void shouldGetProduct() throws Exception {
        ProductResponseDto productResponseDto = getProductResponseDto();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        List<ProductResponseDto> productResponseDtoList = objectMapper
                .readValue(responseBody, new TypeReference<>() {
                });

        Assertions.assertNotNull(productResponseDtoList);
    }

    private ProductRequestDto getProductRequest() {
        return ProductRequestDto.builder()
                .name("iphone")
                .description("iphone")
                .price(BigDecimal.valueOf(1000))
                .build();
    }

    private ProductResponseDto getProductResponseDto() {
        return ProductResponseDto.builder()
                .name(getProductRequest().getName())
                .description(getProductRequest().getDescription())
                .price(getProductRequest().getPrice())
                .build();
    }
}
