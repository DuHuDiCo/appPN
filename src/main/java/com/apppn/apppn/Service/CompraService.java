package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.CompraDTO;
import com.apppn.apppn.Models.Compra;

public interface CompraService {


    public ResponseEntity<Object> crearCompra(CompraDTO compraDTO);

    public ResponseEntity<Object> obtenerCompras();


    public ResponseEntity<Object> editarCompra(Long idCompra, CompraDTO compraDTO);

    public ResponseEntity<Object> eliminarCompra(Long idCompra);

    public ResponseEntity<Object> obtenerCompra(Long idCompra);

    public ResponseEntity<Object> guardarCompraBD(Compra compra);

}
