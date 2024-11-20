package com.apppn.apppn.ServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.CompraDTO;
import com.apppn.apppn.DTO.Request.CompraProductoDTO;
import com.apppn.apppn.Enums.TipoVentaENUM;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.Compra;
import com.apppn.apppn.Models.Producto;
import com.apppn.apppn.Models.ProductoCompra;
import com.apppn.apppn.Models.Proveedor;
import com.apppn.apppn.Models.TipoVenta;
import com.apppn.apppn.Repository.CompraRepository;
import com.apppn.apppn.Repository.ProductoRepository;
import com.apppn.apppn.Repository.ProveedorRepository;
import com.apppn.apppn.Repository.TipoVentaRepository;
import com.apppn.apppn.Service.CompraService;
import com.apppn.apppn.Utils.Functions;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final TipoVentaRepository tipoVentaRepository;
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    private final Functions functions;

    public CompraServiceImpl(CompraRepository compraRepository, TipoVentaRepository tipoVentaRepository,
            ProductoRepository productoRepository, ProveedorRepository proveedorRepository, Functions functions) {
        this.compraRepository = compraRepository;
        this.tipoVentaRepository = tipoVentaRepository;
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
        this.functions = functions;
    }

    @Override
    public ResponseEntity<Object> crearCompra(CompraDTO compraDTO) {

        Proveedor proveedor = proveedorRepository.findById(compraDTO.getIdProveedor()).orElse(null);
        if (Objects.isNull(proveedor)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("PROVEEDOR NO ENCONTRADO"));
        }
        Compra compra = new Compra();
        compra.setMonto(compraDTO.getMonto());
        compra.setProveedor(proveedor);
        compra.setIsPago(false);
        
        compra.setTotalPagar(Objects.isNull(compraDTO.getTotalPagar()) ? 0 : compraDTO.getTotalPagar());
        try {
            compra.setFecha(functions.obtenerFechaYhora());
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR AL PARSEAR LA FECHA Y HORA"));
        }

        for (CompraProductoDTO productoDTO : compraDTO.getProductos()) {
            TipoVenta tipoVenta = tipoVentaRepository.findById(productoDTO.getIdTipoVenta()).orElse(null);
            if (Objects.isNull(tipoVenta)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("TIPO DE VENTA NO ENCONTRADO"));
            }

            Producto producto = productoRepository.findById(productoDTO.getIdProducto()).orElse(null);
            if (Objects.isNull(productoRepository)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("PRODUCTO NO ENCONTRADO"));
            }

            ProductoCompra compraProducto = new ProductoCompra();
            compraProducto.setCantidad(productoDTO.getCantidad());
            
            compraProducto.setProducto(producto);
            compraProducto.setTipoVenta(tipoVenta);


            if(compraProducto.getTipoVenta().getTipoVenta().toUpperCase().equals(TipoVentaENUM.CONTADO.getDato())){

                Double totaCosto = productoDTO.getCosto();


                
                // compraProducto.setCosto();
            }
            compra.agregarProducto(compraProducto);

        }

        compra = compraRepository.save(compra);
        return ResponseEntity.status(HttpStatus.OK).body(compra);

    }

    @Override
    public ResponseEntity<Object> obtenerCompras() {
        List<Compra> compras = compraRepository.findAll();
        if (CollectionUtils.isEmpty(compras)) {
            compras = new ArrayList<>();

        }
        return ResponseEntity.status(HttpStatus.OK).body(compras);
    }

    @Override
    public ResponseEntity<Object> editarCompra(Long idCompra, CompraDTO compraDTO) {
        Compra compraEdit = compraRepository.findById(idCompra).orElse(null);
        if (Objects.isNull(compraEdit)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El compra no existe"));
        }
        compraEdit.setMonto(compraDTO.getMonto());
       
        compraEdit.setTotalPagar(compraDTO.getTotalPagar());

        compraEdit.getProductoCompras().clear();

        for (CompraProductoDTO productoDTO : compraDTO.getProductos()) {
            TipoVenta tipoVenta = tipoVentaRepository.findById(productoDTO.getIdTipoVenta()).orElse(null);
            if (Objects.isNull(tipoVenta)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("TIPO DE VENTA NO ENCONTRADO"));
            }

            Producto producto = productoRepository.findById(productoDTO.getIdProducto()).orElse(null);
            if (Objects.isNull(productoRepository)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("PRODUCTO NO ENCONTRADO"));
            }

            ProductoCompra compraProducto = new ProductoCompra();
            compraProducto.setCantidad(productoDTO.getCantidad());
            compraProducto.setCosto(productoDTO.getCosto());
            compraProducto.setProducto(producto);
            compraProducto.setTipoVenta(tipoVenta);

            compraEdit.agregarProducto(compraProducto);

        }
        compraEdit = compraRepository.save(compraEdit);
        return ResponseEntity.status(HttpStatus.OK).body(compraEdit);

    }

    @Override
    public ResponseEntity<Object> eliminarCompra(Long idCompra) {
        Compra compra = compraRepository.findById(idCompra).orElse(null);
        if (Objects.isNull(compra)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El compra no existe"));
        }
        compraRepository.delete(compra);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessException("El compra ha sido eliminado"));
    }

    @Override
    public ResponseEntity<Object> obtenerCompra(Long idCompra) {
       Compra compra = compraRepository.findById(idCompra).orElse(null);
       if(Objects.isNull(compra)){
           return ResponseEntity.badRequest().body(new ErrorResponse("El compra no existe"));
       }
       return ResponseEntity.status(HttpStatus.OK).body(compra);
    }

    @Override
    public ResponseEntity<Object> guardarCompraBD(Compra compra) {
        compra = compraRepository.save(compra);
        return ResponseEntity.status(HttpStatus.OK).body(compra);
    }
}