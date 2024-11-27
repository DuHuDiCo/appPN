package com.apppn.apppn.ServiceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.ProductDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.Archivos;
import com.apppn.apppn.Models.ClasificacionProducto;
import com.apppn.apppn.Models.Producto;
import com.apppn.apppn.Repository.ProductoRepository;
import com.apppn.apppn.Service.ArchivoService;
import com.apppn.apppn.Service.ClasificacionProductoService;
import com.apppn.apppn.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductoRepository productRepository;
    private final ArchivoService archivoService;
    private final ClasificacionProductoService clasificacionProductoService;

    public ProductServiceImpl(ProductoRepository productRepository, ArchivoService archivoService,
            ClasificacionProductoService clasificacionProductoService) {
        this.productRepository = productRepository;
        this.archivoService = archivoService;
        this.clasificacionProductoService = clasificacionProductoService;
    }

    @Override
    public ResponseEntity<?> createProduct(ProductDTO productDTO) {
        Producto producto = productRepository.findByProducto(productDTO.getProducto());
        if (Objects.nonNull(producto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto ya existe");
        }

        producto = new Producto();
        producto.setProducto(productDTO.getProducto());
        producto.setDescripcion(productDTO.getDescripcion());

        ResponseEntity<?> response = clasificacionProductoService
                .getClasificacionProducto(productDTO.getClasificacionProducto());
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return response;
        }
        ClasificacionProducto clasificacionProducto = (ClasificacionProducto) response.getBody();
        if (Objects.isNull(clasificacionProducto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Clasificacion Producto no encontrado"));
        }
        producto.setClasificacionProducto(clasificacionProducto);

        // IMAGEN PRODUCTO
        if (Objects.nonNull(productDTO.getImagen())) {

            ResponseEntity<?> archivos = archivoService.saveFile(productDTO.getImagen(), "/data/uploads/");
            if (!archivos.getStatusCode().equals(HttpStatus.OK)) {
                return archivos;
            }

            Archivos file = (Archivos) archivos.getBody();
            if (Objects.isNull(file)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Error al subir imagen"));
            }
            producto = productRepository.save(producto);
            producto.agregarImagen(file);
        }

        producto = productRepository.save(producto);

        return ResponseEntity.status(HttpStatus.OK).body(producto);

    }

    @Override
    public ResponseEntity<?> getProducts() {
        List<Producto> productos = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productos);
    }

    @Override
    public ResponseEntity<?> editProduct(Long idProducto, ProductDTO productDTO) {
        Producto product = productRepository.findById(idProducto).orElse(null);
        if (Objects.isNull(product)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no encontrado");
        }
        product.setProducto(productDTO.getProducto());
        product.setDescripcion(productDTO.getDescripcion());
        // IMAGEN PRODUCTO

        ResponseEntity<?> archivos = archivoService.saveFile(productDTO.getImagen(), "/data/uploads/");
        if (!archivos.getStatusCode().equals(HttpStatus.OK)) {
            return archivos;
        }

        Archivos file = (Archivos) archivos.getBody();
        if (Objects.isNull(file)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error al subir imagen"));
        }
        product = productRepository.save(product);
        product.agregarImagen(file);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long idProducto) {
        Producto product = productRepository.findById(idProducto).orElse(null);
        if (Objects.isNull(product)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Producto no encontrado"));
        }
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessException("Producto eliminado"));
    }

}
