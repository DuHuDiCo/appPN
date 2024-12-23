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
import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.PlanPagos;
import com.apppn.apppn.Repository.FacturacionRepository;
import com.apppn.apppn.Repository.PlanPagosRepository;
import com.apppn.apppn.Service.AbonoService;
import com.apppn.apppn.Utils.Functions;

@Service
public class AbonosServiceImpl implements AbonoService {

    private final PlanPagosRepository planPagosRepository;
    private final FacturacionRepository facturacionRepository;
    private final Functions functions;

    public AbonosServiceImpl(PlanPagosRepository planPagosRepository, FacturacionRepository facturacionRepository,
            Functions functions) {
        this.planPagosRepository = planPagosRepository;
        this.facturacionRepository = facturacionRepository;
        this.functions = functions;
    }

    @Override
    public ResponseEntity<?> crearAbono(PagoClienteDTO pagoClientesDto) {
        if (CollectionUtils.isEmpty(pagoClientesDto.getAplicarPagoDTO())) {
            throw new IllegalArgumentException("Debe especificar al menos un pago");
        }

        for (AplicarPagoDTO aplicarPagoDTO : pagoClientesDto.getAplicarPagoDTO()) {
            Facturacion facturacion = facturacionRepository.findById(aplicarPagoDTO.getIdFacturacion())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe la facturacion con id " + aplicarPagoDTO.getIdFacturacion()));
            if (Objects.isNull(facturacion)) {
                throw new IllegalArgumentException(
                        "No existe la facturacion con id " + aplicarPagoDTO.getIdFacturacion());
            }

            List<PlanPagos> planPagos = facturacion.getPlanPagos().stream().filter(pp -> pp.getSaldo() > 0)
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(planPagos)) {
                throw new IllegalArgumentException("No existe plan de pagos");
            }
            functions.calculoPlanPagos(planPagos, aplicarPagoDTO);
            facturacion = facturacionRepository.save(facturacion);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Abono creado"));
    }

}
