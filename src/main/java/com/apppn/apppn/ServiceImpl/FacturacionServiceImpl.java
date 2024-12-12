package com.apppn.apppn.ServiceImpl;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.FacturacionDTO;
import com.apppn.apppn.DTO.Request.FacturacionProductosDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.Inventory;
import com.apppn.apppn.Models.ProductoCompra;
import com.apppn.apppn.Models.ProductoCompraFacturacion;
import com.apppn.apppn.Models.ProductoCompraInventory;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.ClientRepository;
import com.apppn.apppn.Repository.FacturacionRepository;
import com.apppn.apppn.Repository.InventoryRepository;
import com.apppn.apppn.Repository.ProductoCompraInventoryRepository;
import com.apppn.apppn.Service.FacturacionService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;

@Service
public class FacturacionServiceImpl implements FacturacionService {

    private final FacturacionRepository facturacionRepository;
    private final InventoryRepository inventoryRepository;
    private final ClientRepository clientRepository;
    private final Functions functions;
    private final UserService userService;
    private final ProductoCompraInventoryRepository productoCompraInventoryRepository;


    public FacturacionServiceImpl(FacturacionRepository facturacionRepository, InventoryRepository inventoryRepository,
            ClientRepository clientRepository, Functions functions, UserService userService,
            ProductoCompraInventoryRepository productoCompraInventoryRepository) {
        this.facturacionRepository = facturacionRepository;
        this.inventoryRepository = inventoryRepository;
        this.clientRepository = clientRepository;
        this.functions = functions;
        this.userService = userService;
        this.productoCompraInventoryRepository = productoCompraInventoryRepository;
    }

    @Override
    public ResponseEntity<?> cearFacturacion(FacturacionDTO facturacionDTO) {
        Inventory inventory = inventoryRepository.findById(facturacionDTO.getIdInventario()).orElse(null);
        if (Objects.isNull(inventory)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("No existe la Inventario con ese id"));
        }

        if (Objects.nonNull(inventory.getFacturacion())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Ya existe una facturacion para este inventario"));
        }

        Facturacion facturacion = new Facturacion();
        facturacion.setTotalFacturacion(0.0);
        try {
            facturacion.setFecha(functions.obtenerFechaYhora());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String username = functions.obtenerUsernameByToken();
        if (Objects.isNull(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("No tienes permisos para acceder a esta ruta"));
        }
        ResponseEntity<?> response = userService.getUserByEmail(username);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return response;
        }
        User user = (User) response.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("No existe el usuario con ese email"));
        }
        facturacion.setUser(user);



        for (FacturacionProductosDTO producto : facturacionDTO.getProductos()) {
            ProductoCompraInventory productoCompraInventory = inventory.getProductoCompras().stream()
                    .filter(p -> p.getProductoCompra().getIdProductoCompra().equals(producto.getIdProductoCompra())).findFirst()
                    .orElse(null);
            if (Objects.isNull(productoCompraInventory)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("No existe el Producto con ese id"));
            }

            ProductoCompraFacturacion productoCompraFacturacion = new ProductoCompraFacturacion();
            productoCompraFacturacion.setProductoCompraInventory(productoCompraInventory);
            if(producto.getValorVenta() < (productoCompraInventory.getProductoCompra().getCosto() + productoCompraInventory.getProductoCompra().getFlete())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("El valor de venta no puede ser menor que el costo del producto"));
            }
            productoCompraFacturacion.setValorVenta(producto.getValorVenta());
            productoCompraFacturacion.setTotalVenta((producto.getValorVenta() * producto.getCantidad()) - producto.getDescuentoPagoInicial());
            productoCompraFacturacion.setDescuentoPagoInicial(producto.getDescuentoPagoInicial());

            productoCompraFacturacion.setCantidad(producto.getCantidad());
            inventory.setQuantitySinFacturacion(inventory.getQuantity() - producto.getCantidad());

            facturacion.setTotalFacturacion(facturacion.getTotalFacturacion() + producto.getValorVenta());

            Client client = clientRepository.findById(producto.getIdCliente()).orElse(null);

            if (Objects.isNull(client)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("No existe el Cliente con ese id"));

            }

            productoCompraFacturacion.setClient(client);
            productoCompraFacturacion.setValorVenta(producto.getValorVenta());
            facturacion.agregarProductoCompraFacturacion(productoCompraFacturacion);
        }

        facturacion = facturacionRepository.save(facturacion);

        inventory.setFacturacion(facturacion);
        inventory = inventoryRepository.save(inventory);

        return ResponseEntity.status(HttpStatus.OK).body(facturacion);

    }

    @Override
    public ResponseEntity<?> getFacturaciones(Long idUser) {
        User user = null;
        if (idUser > 0L) {
            ResponseEntity<?> response = userService.getUserById(idUser);
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                return response;
            }
            user = (User) response.getBody();
            if (Objects.isNull(user)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("No existe el usuario con ese email"));
            }
        }

        if (idUser == 0L) {
            String username = functions.obtenerUsernameByToken();
            if (Objects.isNull(username)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("No tienes permisos para acceder a esta ruta"));
            }
            ResponseEntity<?> response = userService.getUserByEmail(username);
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                return response;
            }
            user = (User) response.getBody();
            if (Objects.isNull(user)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("No existe el usuario con ese email"));
            }
        }

        List<Facturacion> facturaciones = facturacionRepository.obtejFacturacions(user.getIdUser());
        return ResponseEntity.status(HttpStatus.OK).body(facturaciones);

    }

    @Override
    public ResponseEntity<?> obtenerProductosInventarioByUser() {
        String username = functions.obtenerUsernameByToken();
        if (Objects.isNull(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("No tienes permisos para acceder a esta ruta"));
        }
        ResponseEntity<?> response = userService.getUserByEmail(username);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return response;
        }
        User user = (User) response.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("No existe el usuario con ese email"));
        }
       
        List<Inventory> inventories = inventoryRepository.listarInventarioConFacturacionByUser(user.getIdUser());
        if(CollectionUtils.isEmpty(inventories)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(inventories);

        }

         List<ProductoCompraInventory> productoCompraInventories = productoCompraInventoryRepository.obtenerProductosInventarioSinFacturacion(user.getIdUser());

        return ResponseEntity.status(HttpStatus.OK).body(productoCompraInventories);
    }

}
