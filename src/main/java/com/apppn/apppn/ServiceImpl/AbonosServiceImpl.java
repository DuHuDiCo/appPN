package com.apppn.apppn.ServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.AplicarPagoDTO;
import com.apppn.apppn.DTO.Request.PagoClienteDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.PlanPagos;
import com.apppn.apppn.Repository.ClientRepository;
import com.apppn.apppn.Repository.FacturacionRepository;
import com.apppn.apppn.Repository.PlanPagosRepository;
import com.apppn.apppn.Service.AbonoService;
import com.apppn.apppn.Utils.Functions;

@Service
public class AbonosServiceImpl implements AbonoService {

    private final PlanPagosRepository planPagosRepository;
    private final FacturacionRepository facturacionRepository;
    private final Functions functions;
    private final ClientRepository clientRepository;

    public AbonosServiceImpl(PlanPagosRepository planPagosRepository, FacturacionRepository facturacionRepository,
            Functions functions, ClientRepository clientRepository) {
        this.planPagosRepository = planPagosRepository;
        this.facturacionRepository = facturacionRepository;
        this.functions = functions;
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<?> crearAbono(PagoClienteDTO pagoClientesDto) {
        if (CollectionUtils.isEmpty(pagoClientesDto.getAplicarPagoDTO())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("No hay pagos a aplicar"));
        }

        for (AplicarPagoDTO aplicarPagoDTO : pagoClientesDto.getAplicarPagoDTO()) {
            Facturacion facturacion = facturacionRepository.findById(aplicarPagoDTO.getIdFacturacion())
                    .orElse(null);
            if (Objects.isNull(facturacion)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Facturacion no encontrada"));
            }

            Client client = clientRepository.findById(aplicarPagoDTO.getIdCliente()).orElse(null);
            if (Objects.isNull(client)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Cliente no encontrado"));
            }

            PlanPagos planPagos = client.getPlanPagos().stream()
                    .filter(pp -> pp.getFacturacion().getIdFacturacion().equals(aplicarPagoDTO.getIdFacturacion()))
                    .findFirst().orElse(null);

            if (Objects.isNull(planPagos)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Plan de pagos no encontrado"));
            }

            functions.calculoPlanPagos(planPagos.getCuotas(), aplicarPagoDTO);
            client = clientRepository.save(client);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Abono creado"));
    }

}
