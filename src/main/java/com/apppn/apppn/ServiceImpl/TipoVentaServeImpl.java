package com.apppn.apppn.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.TipoVenta;
import com.apppn.apppn.Repository.TipoVentaRepository;
import com.apppn.apppn.Service.TipoVentaService;

@Service
public class TipoVentaServeImpl implements TipoVentaService {

    private final TipoVentaRepository tipoVentaRepository;

    public TipoVentaServeImpl(TipoVentaRepository tipoVentaRepository) {
        this.tipoVentaRepository = tipoVentaRepository;
    }

    @Override
    public ResponseEntity<?> crearTipoVenta(Map<String, String> tVenta) {
        TipoVenta tipoVenta = tipoVentaRepository.findByTipoVenta(tVenta.get(tVenta.keySet().iterator().next()));
        if (Objects.nonNull(tipoVenta)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("El tipo de venta ya existe"));
        }

        tipoVenta = new TipoVenta();
        tipoVenta.setTipoVenta(tVenta.get(tVenta.keySet().iterator().next()));
        tipoVenta = tipoVentaRepository.save(tipoVenta);
        return ResponseEntity.status(HttpStatus.OK).body(tipoVenta);
    }

    @Override
    public ResponseEntity<?> obtenerTipoVentas() {
        List<TipoVenta> tipoVentas = tipoVentaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tipoVentas);
    }

    @Override
    public ResponseEntity<?> eliminarTipoVenta(Long idTipoVenta) {
        TipoVenta tipoVenta = tipoVentaRepository.findById(idTipoVenta).orElse(null);
        if (Objects.isNull(tipoVenta)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("El tipo de venta no existe"));
        }
        tipoVentaRepository.delete(tipoVenta);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("El tipo de venta ha sido eliminado"));
    }

    @Override
    public ResponseEntity<?> obtenerTipoVenta(String tipoVenta) {
        String[] datos = tipoVenta.split(" ");
        List<TipoVenta> tipoVentas = tipoVentaRepository.findByNombreTipoVenta(datos[0]);
        if (tipoVentas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("El tipo de venta no existe"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(tipoVentas);
    }

}
