//package com.georgik1.inventoryservice.init;//package com.georgik1.inventoryservice.init;
//
//import com.georgik1.inventoryservice.model.Inventory;
//import com.georgik1.inventoryservice.repository.InventoryRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InitData implements CommandLineRunner {
//
//    /*manually adding data to DB so that I don't have to create a separate endpoint(for testing purposes)*/
//
//    private final InventoryRepository inventoryRepository;
//
//    public InitData(InventoryRepository inventoryRepository) {
//        this.inventoryRepository = inventoryRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Inventory inventory = new Inventory();
//        inventory.setSkuCode("iphone");
//        inventory.setQuantity(10);
//
//        Inventory inventory1 = new Inventory();
//        inventory1.setSkuCode("MacBook");
//        inventory1.setQuantity(0);
//
//        inventoryRepository.save(inventory);
//        inventoryRepository.save(inventory1);
//    }
//}
