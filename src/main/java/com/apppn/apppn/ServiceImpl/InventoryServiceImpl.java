package com.apppn.apppn.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
import com.apppn.apppn.Security.Security.JwtUtils;
import com.apppn.apppn.Service.InventoryService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserService userService;
    private final Functions functions;
    private final ProductoCompraInventoryRepository productoCompraInventoryRepository;
    private final HttpServletRequest request;
    private final JwtUtils jwtUtils;

    


  

    public InventoryServiceImpl(InventoryRepository inventoryRepository, UserService userService, Functions functions,
            ProductoCompraInventoryRepository productoCompraInventoryRepository, HttpServletRequest request,
            JwtUtils jwtUtils) {
        this.inventoryRepository = inventoryRepository;
        this.userService = userService;
        this.functions = functions;
        this.productoCompraInventoryRepository = productoCompraInventoryRepository;
        this.request = request;
        this.jwtUtils = jwtUtils;
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
    public ResponseEntity<?> getInventories( Boolean isnull) {


        String token = request.getAttribute("token").toString();

        String username = jwtUtils.extractUsername(token);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Token no valido"));
        }

        ResponseEntity<?> response = userService.getUserByEmail(username);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return response;
        }

        User user = (User) response.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Usuario no encontrado"));
        }


        List<Inventory> inventories = isnull?inventoryRepository.listarInventarioSinFacturacionByUser(user.getIdUser()): inventoryRepository.listarInventarioSinFacturacionByUser(user.getIdUser());

        for (Inventory inventory : inventories) {
            inventory.setProductoCompras(inventory.getProductoCompras().stream().filter(p->p.getUser().getIdUser().equals(user.getIdUser())).collect(Collectors.toList()));
        }



        return ResponseEntity.status(HttpStatus.OK).body(inventories);

    }

   



    
}
