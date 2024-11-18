package com.apppn.apppn.ServiceImpl;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.ProductDTO;
import com.apppn.apppn.Models.Producto;
import com.apppn.apppn.Repository.ProductoRepository;
import com.apppn.apppn.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductoRepository productRepository;

    public ProductServiceImpl(ProductoRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> createProduct(ProductDTO productDTO) {
        Producto producto = productRepository.findByProducto(productDTO.getProducto());
        if (Objects.nonNull(producto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto ya existe");
        }

        Producto product = new Producto();
        product.setProducto(productDTO.getProducto());
        product.setDescripcion(productDTO.getDescripcion());
        // IMAGEN PRODUCTO

        return null;

    }

    @Override
    public ResponseEntity<?> getProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProducts'");
    }

    @Override
    public ResponseEntity<?> editProduct(Long idProducto, ProductDTO productDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editProduct'");
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long idProducto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

}
