package com.apppn.apppn.Service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ClasificacionProductoService {

    public ResponseEntity<?> getClasificacionProducto();

    public ResponseEntity<?> saveClasificacionProducto(Map<String, String> clasificacionProductoDTO);

    public ResponseEntity<?> deleteClasificacionProducto(Long idClasificacionProducto);
    public ResponseEntity<?> getClasificacionProducto(Long idClasificacionProducto);

}
