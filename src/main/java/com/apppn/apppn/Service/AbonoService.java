package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.PagoClienteDTO;
import com.apppn.apppn.DTO.Response.AbonoDTO;

public interface AbonoService {


    public ResponseEntity<?> crearAbono(AbonoDTO abonoDTO);

    

}
