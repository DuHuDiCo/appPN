package com.apppn.apppn.ServiceImpl;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.InventoryDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Inventory;
import com.apppn.apppn.Models.ProductoCompra;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.InventoryRepository;
import com.apppn.apppn.Service.InventoryService;
import com.apppn.apppn.Service.UserService;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserService userService;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, UserService userService) {
        this.inventoryRepository = inventoryRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> saveInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setDateInventory(inventoryDTO.getDateInventory());
        inventory.setTotalInventoryValue(inventoryDTO.getTotalInventoryValue());
        inventory.setTotalCostValue(inventoryDTO.getTotalCostValue());
        inventory.setQuantity(inventoryDTO.getQuantity());

        ResponseEntity<?> user = userService.getUserById(inventoryDTO.getUserId());
        if (!user.getStatusCode().equals(HttpStatus.OK)) {
            return user;
        }

        User userDB = (User) user.getBody();
        if (Objects.isNull(userDB)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("User not found"));
        }
        inventory.setUser(userDB);

        inventory = inventoryRepository.save(inventory);


        for(ProductoCompra pro : inventoryDTO.getProductos()){
            inventory.agregarProducto(pro);

        }

        inventory = inventoryRepository.save(inventory);

        return ResponseEntity.status(HttpStatus.OK).body(inventory);

    }

}
