package com.apppn.apppn.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.InventoryDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Inventory;
import com.apppn.apppn.Models.ProductoCompra;
import com.apppn.apppn.Models.ProductoCompraFacturacion;
import com.apppn.apppn.Models.ProductoCompraInventory;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.InventoryRepository;
import com.apppn.apppn.Repository.ProductoCompraInventoryRepository;
import com.apppn.apppn.Service.InventoryService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserService userService;
    private final Functions functions;
    private final ProductoCompraInventoryRepository productoCompraInventoryRepository;

    

    public InventoryServiceImpl(InventoryRepository inventoryRepository, UserService userService, Functions functions,
            ProductoCompraInventoryRepository productoCompraInventoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.userService = userService;
        this.functions = functions;
        this.productoCompraInventoryRepository = productoCompraInventoryRepository;
    }

    @Override
    public ResponseEntity<?> saveInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setDateInventory(inventoryDTO.getDateInventory());
        inventory.setTotalInventoryValue(inventoryDTO.getTotalInventoryValue());
        
        inventory.setQuantity(inventoryDTO.getQuantity());



        for (ProductoCompra producto : inventoryDTO.getProductos()) {
            ProductoCompraInventory productoCompraInventory = new ProductoCompraInventory();
            productoCompraInventory.setProductoCompra(producto);
            productoCompraInventory.setInventory(inventory);
            productoCompraInventory.setUser(producto.getUser());
            inventory.agregarProductoCompra(productoCompraInventory);
        }

        

        productoCompraInventoryRepository.saveAll(inventory.getProductoCompras());
        return ResponseEntity.status(HttpStatus.OK).body(inventory);

    }

    @Override
    public ResponseEntity<?> getInventories(Long idUser) {

        String username = null;
        User user = null;
        ResponseEntity<?> userResponse = null;

        if (Objects.isNull(idUser)) {
            username = functions.obtenerUsernameByToken();
        }

        if (Objects.nonNull(username)) {
            userResponse = userService.getUserByEmail(username);
            if (!userResponse.getStatusCode().equals(HttpStatus.OK)) {
                return userResponse;
            }

        } else {
            userResponse = userService.getUserById(idUser);
            if (!userResponse.getStatusCode().equals(HttpStatus.OK)) {
                return userResponse;
            }
        }

        user = (User) userResponse.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("El usuario no existe"));
        }

        List<Inventory> inventories = inventoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(inventories);

    }

   



    
}
