package com.apppn.apppn.Service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface TipoVentaService {


    public ResponseEntity<?> crearTipoVenta(Map<String, String> tipoVenta);  

    public ResponseEntity<?> obtenerTipoVentas();

    public ResponseEntity<?> eliminarTipoVenta(Long idTipoVenta);
}
