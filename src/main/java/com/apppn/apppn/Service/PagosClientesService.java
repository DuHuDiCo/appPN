package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.PagoClienteDTO;

public interface PagosClientesService {


    public ResponseEntity<?> crearPagoClientes(PagoClienteDTO pagoClientesDto);

    public ResponseEntity<?> consultarPagoClientes(Long idPagoCliente);

    public ResponseEntity<?> listarPagosClientes();

    public ResponseEntity<?> eliminarPagoClientes(Long idPagoCliente);

}
