package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.PagoDTO;

public interface PagoService {


    public ResponseEntity<?> crearPago(Long idCompra, PagoDTO pago);
    
    public ResponseEntity<?> obtenerPagos();

    public ResponseEntity<?> eliminarPago(Long idPago, Long idInventario);


}
