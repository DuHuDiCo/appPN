package com.apppn.apppn.ServiceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.FacturacionDTO;
import com.apppn.apppn.DTO.Request.FacturacionProductosDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.Inventory;
import com.apppn.apppn.Models.ProductoCompra;
import com.apppn.apppn.Models.ProductoCompraFacturacion;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.ClientRepository;
import com.apppn.apppn.Repository.FacturacionRepository;
import com.apppn.apppn.Repository.InventoryRepository;
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

   



    public FacturacionServiceImpl(FacturacionRepository facturacionRepository, InventoryRepository inventoryRepository,
            ClientRepository clientRepository, Functions functions, UserService userService) {
        this.facturacionRepository = facturacionRepository;
        this.inventoryRepository = inventoryRepository;
        this.clientRepository = clientRepository;
        this.functions = functions;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> cearFacturacion(FacturacionDTO facturacionDTO) {
        Inventory inventory = inventoryRepository.findById(facturacionDTO.getIdInventario()).orElse(null);
        if(Objects.isNull(inventory)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("No existe la Inventario con ese id"));
        }

        if(Objects.nonNull(inventory.getFacturacion())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Ya existe una facturacion para este inventario"));
        }


        Facturacion facturacion = new Facturacion();

        for(FacturacionProductosDTO producto : facturacionDTO.getProductos()){
            ProductoCompra productoCompra = inventory.getProductoCompras().stream().filter(p -> p.getIdProductoCompra().equals(producto.getIdProductoCompra())).findFirst().orElse(null);
            if(Objects.isNull(productoCompra)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("No existe el Producto con ese id"));
            }

            ProductoCompraFacturacion productoCompraFacturacion = new ProductoCompraFacturacion();
            productoCompraFacturacion.setProductoCompra(productoCompra);
            productoCompraFacturacion.setValorVenta(producto.getValorVenta());

            facturacion.setTotalFacturacion(facturacion.getTotalFacturacion() + producto.getValorVenta());
            
            Client client = clientRepository.findById(producto.getIdCliente()).orElse(null);

            if (Objects.isNull(client)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("No existe el Cliente con ese id"));
                
            }

            productoCompraFacturacion.setClient(client);
            productoCompraFacturacion.setValorVenta(producto.getValorVenta());
            facturacion.agregarProductoCompraFacturacion(productoCompraFacturacion);
        }


        facturacion = facturacionRepository.save(facturacion);
        return ResponseEntity.status(HttpStatus.OK).body(facturacion);

    }

    @Override
    public ResponseEntity<?> getFacturaciones() {

        String username = functions.obtenerUsernameByToken();
        if(Objects.isNull(username)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("No tienes permisos para acceder a esta ruta"));
        }
        
        ResponseEntity<?> response = userService.getUserByEmail(username);
        if(!response.getStatusCode().equals(HttpStatus.OK)){
            return response;
        }
        User user = (User) response.getBody();
        if(Objects.isNull(user)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("No existe el usuario con ese email"));
        }

        List<Facturacion> facturaciones = facturacionRepository.findByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(facturaciones);


    }





}
