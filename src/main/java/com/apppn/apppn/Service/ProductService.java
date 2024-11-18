package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.ProductDTO;

public interface ProductService {


    public ResponseEntity<?> createProduct(ProductDTO productDTO);


    public ResponseEntity<?> getProducts();

    public ResponseEntity<?> editProduct(Long idProducto, ProductDTO productDTO);

    public ResponseEntity<?> deleteProduct(Long idProducto);


}
