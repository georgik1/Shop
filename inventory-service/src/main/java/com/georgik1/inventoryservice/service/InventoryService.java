package com.georgik1.inventoryservice.service;

import com.georgik1.inventoryservice.model.dto.InventoryResponseDto;

import java.util.List;

public interface InventoryService {

    List<InventoryResponseDto> isInStock(List<String> skuCode);

}
