package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.CompraDTO;

public interface CompraService {


    public ResponseEntity<Object> crearCompra(CompraDTO compraDTO);

    public ResponseEntity<Object> obtenerCompras();

}
