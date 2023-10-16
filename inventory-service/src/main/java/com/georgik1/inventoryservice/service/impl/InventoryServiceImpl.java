package com.georgik1.inventoryservice.service.impl;

import com.georgik1.inventoryservice.repository.InventoryRepository;
import com.georgik1.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
       return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
