package com.georgik1.inventoryservice.controller;

import com.georgik1.inventoryservice.model.dto.InventoryResponseDto;
import com.georgik1.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //with @PathVariable:
    //http://localhost:8082/api/inventory/iphone, MacBook
    //with request param(requires List):
    // "skuCode" comes form OrderServiceImpl - builder
    //http://localhost:8082/api/inventory?skuCode=iphone&skuCode=MacBook
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDto> isInStock(@RequestParam("skuCode") List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
