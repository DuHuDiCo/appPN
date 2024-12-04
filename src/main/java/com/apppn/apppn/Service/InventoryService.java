package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.InventoryDTO;

public interface InventoryService {


    public ResponseEntity<?> saveInventory(InventoryDTO inventoryDTO);


    public ResponseEntity<?> getInventories(Long idUser, Boolean isnull);


    

}
