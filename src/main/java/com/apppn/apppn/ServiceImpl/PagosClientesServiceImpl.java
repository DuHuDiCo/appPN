package com.apppn.apppn.ServiceImpl;

import java.text.ParseException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.PagoClienteDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.PagoClientes;
import com.apppn.apppn.Models.TipoPago;
import com.apppn.apppn.Repository.PagosClientesRepository;
import com.apppn.apppn.Repository.TipoPagoRepository;
import com.apppn.apppn.Service.PagosClientesService;
import com.apppn.apppn.Utils.Functions;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;

@Service
public class PagosClientesServiceImpl implements PagosClientesService {

    private final PagosClientesRepository pagosClientesRepository;
    private final TipoPagoRepository tipoPagoRepository;
    private final Functions functions;

    

    public PagosClientesServiceImpl(PagosClientesRepository pagosClientesRepository,
            TipoPagoRepository tipoPagoRepository, Functions functions) {
        this.pagosClientesRepository = pagosClientesRepository;
        this.tipoPagoRepository = tipoPagoRepository;
        this.functions = functions;
    }

    @Override
    public ResponseEntity<?> crearPagoClientes(PagoClienteDTO pagoClientesDto) {
        PagoClientes pagosClientes = new PagoClientes();
        pagosClientes.setValor(pagoClientesDto.getValor());
        pagosClientes.setNumeroRecibo(pagoClientesDto.getNumeroRecibo());


        TipoPago tipoPago = tipoPagoRepository.findByNombreTipoPago(pagoClientesDto.getTipoPago());
        if (Objects.isNull(tipoPago)) {
            tipoPago = new TipoPago();
            tipoPago.setNombreTipoPago(pagoClientesDto.getTipoPago());
            tipoPago = tipoPagoRepository.save(tipoPago);
        }

        pagosClientes.setTipoPago(tipoPago);
        try {
            pagosClientes.setFechaPago(functions.obtenerFechaYhora());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("ERROR AL PARSEAR LA FECHA Y HORA"));
        }

        if(!CollectionUtils.isEmpty(pagoClientesDto.getAplicarPagoDTO())) {

        }

        pagosClientes = pagosClientesRepository.save(pagosClientes);
        return ResponseEntity.status(HttpStatus.OK).body(pagosClientes);

    }

    @Override
    public ResponseEntity<?> consultarPagoClientes(Long idPagoCliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarPagoClientes'");
    }

    @Override
    public ResponseEntity<?> listarPagosClientes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPagosClientes'");
    }

    @Override
    public ResponseEntity<?> eliminarPagoClientes(Long idPagoCliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPagoClientes'");
    }







}
