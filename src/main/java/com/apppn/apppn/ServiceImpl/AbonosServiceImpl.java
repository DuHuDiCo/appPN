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
import com.apppn.apppn.DTO.Response.AbonoDTO;
import com.apppn.apppn.DTO.Response.CuotasRequest;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.Cuotas;
import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.PagoClientes;
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
    public ResponseEntity<?> crearAbono(AbonoDTO abonoDTO) {
        if (CollectionUtils.isEmpty(abonoDTO.getCuotas())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("No hay pagos a aplicar"));
        }
        Client client = clientRepository.findById(abonoDTO.getIdCliente()).orElse(null);
        if (Objects.isNull(client)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Cliente no encontrado"));
        }

        for (CuotasRequest cuotasDto : abonoDTO.getCuotas()) {
            Facturacion facturacion = facturacionRepository.findById(cuotasDto.getIdFacturacion())
                    .orElse(null);
            if (Objects.isNull(facturacion)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Facturacion no encontrada"));
            }
            PagoClientes pagoClientes = facturacion.getPagoClientes().stream()
                    .filter(pc -> pc.getIdPagoCliente().equals(cuotasDto.getIdPagoCliente())).findFirst().orElse(null);

            if (Objects.isNull(pagoClientes)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("PagoClientes no encontrado"));
            }

            PlanPagos planPagos = client.getPlanPagos().stream()
                    .filter(pp -> pp.getFacturacion().getIdFacturacion().equals(cuotasDto.getIdFacturacion()))
                    .findFirst().orElse(null);

            if (Objects.isNull(planPagos)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Plan de pagos no encontrado"));
            }

            Cuotas cuotas = planPagos.getCuotas().stream().filter(c -> c.getIdCuota().equals(cuotasDto.getIdCuota()))
                    .findFirst().orElse(null);

            if (Objects.isNull(cuotas)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Cuota no encontrada"));
            }

            if (cuotasDto.getValor() > cuotas.getSaldo()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Valor de abono no puede ser mayor al saldo en la cuota con ID:"
                                .concat(cuotas.getIdCuota().toString())));

            }
            cuotas.setSaldo(cuotas.getSaldo() - cuotasDto.getValor());

            pagoClientes.setIsAplicado(true);
            facturacion = facturacionRepository.save(facturacion);

            client = clientRepository.save(client);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Abono creado"));
    }

}
