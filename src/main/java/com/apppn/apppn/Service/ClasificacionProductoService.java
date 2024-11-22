package com.apppn.apppn.Service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.ClasificacionDTO;

public interface ClasificacionProductoService {

    public ResponseEntity<?> getClasificacionProducto();

    public ResponseEntity<?> saveClasificacionProducto(ClasificacionDTO clasificacionProductoDTO);

    public ResponseEntity<?> deleteClasificacionProducto(Long idClasificacionProducto);
    public ResponseEntity<?> getClasificacionProducto(Long idClasificacionProducto);

}
