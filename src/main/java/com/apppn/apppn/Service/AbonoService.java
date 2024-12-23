package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.PagoClienteDTO;

public interface AbonoService {


    public ResponseEntity<?> crearAbono(PagoClienteDTO pagoClientesDto);

    

}
