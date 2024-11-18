package com.apppn.apppn.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.PagoDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.Archivos;
import com.apppn.apppn.Models.Compra;
import com.apppn.apppn.Models.Pago;
import com.apppn.apppn.Repository.PagoRepository;
import com.apppn.apppn.Service.ArchivoService;
import com.apppn.apppn.Service.CompraService;
import com.apppn.apppn.Service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ArchivoService archivosService;
    private final CompraService compraService;
    

    

    public PagoServiceImpl(PagoRepository pagoRepository, ArchivoService archivosService, CompraService compraService) {
        this.pagoRepository = pagoRepository;
        this.archivosService = archivosService;
        this.compraService = compraService;
    }

    @Override
    public ResponseEntity<?> crearPago(Long idCompra, PagoDTO pagoDTO) {
        ResponseEntity<Object> response = compraService.obtenerCompra(idCompra);
        if(!response.getStatusCode().equals(HttpStatus.OK)){
            return response;
        }

        Compra compra = (Compra) response.getBody();
        if(Objects.isNull(compra)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("El compra no existe"));
        }

        Pago pago = new Pago();
        pago.setTotalPago(pagoDTO.getTotalPago());

        ResponseEntity<?> responseArchivos = archivosService.saveFile(pagoDTO.getArchivo(), "/data/uploads/");
        if(!responseArchivos.getStatusCode().equals(HttpStatus.OK)){
            return responseArchivos;
        }
        Archivos archivos = (Archivos) responseArchivos.getBody();

        pago.setArchivos(archivos);
        compra.setPago(pago);

        ResponseEntity<?> compraResponse = compraService.guardarCompraBD(compra);
        if(!compraResponse.getStatusCode().equals(HttpStatus.OK)){
            return compraResponse;
        }

        return ResponseEntity.status(HttpStatus.OK).body(compra);
    }

    @Override
    public ResponseEntity<?> obtenerPagos() {
       List<Pago> pagos = pagoRepository.findAll();
       if(Objects.isNull(pagos)){
           pagos = new ArrayList<>();
       }
       return ResponseEntity.status(HttpStatus.OK).body(pagos);
    }

    @Override
    public ResponseEntity<?> eliminarPago(Long idCompra, Long idPago) {
       ResponseEntity<Object> response = compraService.obtenerCompra(idCompra);
       if(!response.getStatusCode().equals(HttpStatus.OK)){
           return response;
       }

       Compra compra = (Compra) response.getBody();
       if(Objects.isNull(compra)){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("El compra no existe"));
       }
       compra.setPago(null);

       ResponseEntity<?> compraResponse = compraService.guardarCompraBD(compra);
       if(!compraResponse.getStatusCode().equals(HttpStatus.OK)){
           return compraResponse;
       }

       return ResponseEntity.status(HttpStatus.OK).body(new SuccessException("El pago ha sido eliminado"));

       
    }

}
