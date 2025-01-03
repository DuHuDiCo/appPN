package com.apppn.apppn.ServiceImpl;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.apppn.apppn.DTO.Request.AplicarPagoDTO;
import com.apppn.apppn.DTO.Request.PagoClienteDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Archivos;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.PagoClientes;
import com.apppn.apppn.Models.PlanPagos;
import com.apppn.apppn.Models.TipoPago;
import com.apppn.apppn.Repository.ClientRepository;
import com.apppn.apppn.Repository.FacturacionRepository;
import com.apppn.apppn.Repository.PagosClientesRepository;
import com.apppn.apppn.Repository.TipoPagoRepository;
import com.apppn.apppn.Service.ArchivoService;
import com.apppn.apppn.Service.FacturacionService;
import com.apppn.apppn.Service.PagosClientesService;
import com.apppn.apppn.Utils.Functions;
import com.apppn.apppn.Utils.SaveFiles;


@Service
public class PagosClientesServiceImpl implements PagosClientesService {

    private final PagosClientesRepository pagosClientesRepository;
    private final TipoPagoRepository tipoPagoRepository;
    private final Functions functions;
    private final ArchivoService archivoService;
    private final FacturacionService facturacionService;
    private final FacturacionRepository facturacionRepository;
    private final ClientRepository clientRepository;
    private final SaveFiles  saveFiles;

   
   

    

    public PagosClientesServiceImpl(PagosClientesRepository pagosClientesRepository,
            TipoPagoRepository tipoPagoRepository, Functions functions, ArchivoService archivoService,
            FacturacionService facturacionService, FacturacionRepository facturacionRepository,
            ClientRepository clientRepository, SaveFiles saveFiles) {
        this.pagosClientesRepository = pagosClientesRepository;
        this.tipoPagoRepository = tipoPagoRepository;
        this.functions = functions;
        this.archivoService = archivoService;
        this.facturacionService = facturacionService;
        this.facturacionRepository = facturacionRepository;
        this.clientRepository = clientRepository;
        this.saveFiles = saveFiles;
    }

    @Override
    public ResponseEntity<?> crearPagoClientes(PagoClienteDTO pagoClientesDto) {
        PagoClientes pagosClientes = new PagoClientes();
        pagosClientes.setValor(pagoClientesDto.getValor());
        pagosClientes.setNumeroRecibo(pagoClientesDto.getNumeroRecibo());

        TipoPago tipoPago = tipoPagoRepository.findByNombreTipoPago(pagoClientesDto.getTipoPago().toUpperCase());
        if (Objects.isNull(tipoPago)) {
            tipoPago = new TipoPago();
            tipoPago.setNombreTipoPago(pagoClientesDto.getTipoPago().toUpperCase());
            tipoPago = tipoPagoRepository.save(tipoPago);
        }

        pagosClientes.setTipoPago(tipoPago);
        try {
            pagosClientes.setFechaPago(functions.obtenerFechaYhora());

            MultipartFile file = saveFiles.convertirFile(pagoClientesDto.getComprobante());

            ResponseEntity<?> response = archivoService.saveFile(file, "/data/uploads/");
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                return response;
            }
            Archivos archivos = (Archivos) response.getBody();
            if (Objects.isNull(archivos)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Error al guardar el archivo"));
            }
            pagosClientes.setArchivos(archivos);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR AL PARSEAR LA FECHA Y HORA"));
        }

        if (!CollectionUtils.isEmpty(pagoClientesDto.getAplicarPagoDTO())) {
            for (AplicarPagoDTO aplicarPagoDTO : pagoClientesDto.getAplicarPagoDTO()) {
                ResponseEntity<?> response = facturacionService.getFacturacion(aplicarPagoDTO.getIdFacturacion());

                if(!response.getStatusCode().equals(HttpStatus.OK)){
                    return response;
                }

                Facturacion facturacion = (Facturacion) response.getBody();
                
                if (Objects.isNull(facturacion)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ErrorResponse("Facturacion no encontrada"));
                }



                Client client = clientRepository.findById(aplicarPagoDTO.getIdCliente()).orElse(null);
                if (Objects.isNull(client)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Cliente no encontrado"));
                }



                PlanPagos planPagos = client.getPlanPagos().stream().filter(pp->pp.getFacturacion().getIdFacturacion().equals(aplicarPagoDTO.getIdFacturacion())).findFirst().orElse(null);

                if(Objects.isNull(planPagos)){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Plan de pagos no encontrado"));
                }

                functions.calculoPlanPagos(planPagos.getCuotas(), aplicarPagoDTO);
                client = clientRepository.save(client);


            }
            pagosClientes.setIsAplicado(true);
        }
        pagosClientes.setIsAplicado(false);

        pagosClientes = pagosClientesRepository.save(pagosClientes);
        return ResponseEntity.status(HttpStatus.OK).body(pagosClientes);

    }

    @Override
    public ResponseEntity<?> consultarPagoClientes(Long idCliente) {
        List<PagoClientes> pagosClientes = pagosClientesRepository.findByClient(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(pagosClientes); 
    }

    @Override
    public ResponseEntity<?> listarPagosClientes() {
        List<PagoClientes> pagosClientes = pagosClientesRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pagosClientes);
    }

    @Override
    public ResponseEntity<?> eliminarPagoClientes(Long idPagoCliente) {
        PagoClientes pagoClientes = pagosClientesRepository.findById(idPagoCliente).orElse(null);
        if(Objects.isNull(pagoClientes)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("PagoClientes no encontrado"));
        }
        pagosClientesRepository.delete(pagoClientes);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("PagoClientes eliminado"));
    }

    @Override
    public ResponseEntity<?> pagosSinAplicar() {
        List<PagoClientes> pagosClientes = pagosClientesRepository.findByIsAplicado(false);
        return ResponseEntity.status(HttpStatus.OK).body(pagosClientes);
    }

}
