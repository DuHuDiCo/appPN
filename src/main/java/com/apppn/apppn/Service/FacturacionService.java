package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.FacturacionDTO;

public interface FacturacionService {

    public ResponseEntity<?> cearFacturacion(FacturacionDTO facturacionDTO);

    public ResponseEntity<?> getFacturaciones();

}
