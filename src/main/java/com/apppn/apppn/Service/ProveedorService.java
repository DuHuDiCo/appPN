package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.ProveedorDTO;


public interface ProveedorService {


    public ResponseEntity<?> createProveedor(ProveedorDTO proveedor);

    public ResponseEntity<?> getProveedores();

    public ResponseEntity<?> editProveedor(Long idProveedor, ProveedorDTO proveedor);

    public ResponseEntity<?> deleteProveedor(Long idProveedor);
    

}
