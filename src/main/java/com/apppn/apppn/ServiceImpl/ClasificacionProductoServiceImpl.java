package com.apppn.apppn.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.ClasificacionProducto;
import com.apppn.apppn.Repository.ClasificacionRepository;
import com.apppn.apppn.Service.ClasificacionProductoService;

@Service
public class ClasificacionProductoServiceImpl implements ClasificacionProductoService {


    private final ClasificacionRepository clasificacionRepository;

    public ClasificacionProductoServiceImpl(ClasificacionRepository clasificacionRepository) {
        this.clasificacionRepository = clasificacionRepository;
    }
   
    @Override
    public ResponseEntity<?> getClasificacionProducto() {
        List<ClasificacionProducto> clasificacionProductos = clasificacionRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clasificacionProductos);
    }

    @Override
    public ResponseEntity<?> saveClasificacionProducto(Map<String, String> clasificacionProductoDTO) {
        ClasificacionProducto clasificacionProducto = clasificacionRepository.findByClasificacionProducto(clasificacionProductoDTO.get(clasificacionProductoDTO.keySet().iterator().next()));
        if(Objects.nonNull(clasificacionProducto)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Clasificacion Producto ya existe"));
        }

        clasificacionProducto = new ClasificacionProducto();
        clasificacionProducto.setClasificacionProducto(clasificacionProductoDTO.get(clasificacionProductoDTO.keySet().iterator().next()));
        clasificacionProducto = clasificacionRepository.save(clasificacionProducto);
        return ResponseEntity.status(HttpStatus.OK).body(clasificacionProducto);
    }

    @Override
    public ResponseEntity<?> deleteClasificacionProducto(Long idClasificacionProducto) {
        ClasificacionProducto clasificacionProducto = clasificacionRepository.findById(idClasificacionProducto).orElse(null);
        if(Objects.isNull(clasificacionProducto)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Clasificacion Producto no encontrado"));
        }
        clasificacionRepository.delete(clasificacionProducto);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessException("Clasificacion Producto eliminado"));
    }

}
