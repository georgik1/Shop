package com.georgik1.inventoryservice.repository;

import com.georgik1.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Transactional(readOnly = true)
    Optional<Inventory> findBySkuCode(String skuCode);
}
