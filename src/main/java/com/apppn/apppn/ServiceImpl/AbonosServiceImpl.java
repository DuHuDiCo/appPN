package com.apppn.apppn.ServiceImpl;

import org.checkerframework.checker.units.qual.radians;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.AplicarPagoDTO;
import com.apppn.apppn.DTO.Request.PagoClienteDTO;
import com.apppn.apppn.Repository.PlanPagosRepository;
import com.apppn.apppn.Service.AbonoService;

@Service
public class AbonosServiceImpl implements AbonoService {


    private final PlanPagosRepository planPagosRepository;

    


    public AbonosServiceImpl(PlanPagosRepository planPagosRepository) {
        this.planPagosRepository = planPagosRepository;
    }




    @Override
    public void crearAbono(PagoClienteDTO pagoClientesDto) {
       if (CollectionUtils.isEmpty(pagoClientesDto.getAplicarPagoDTO())) {
           throw new IllegalArgumentException("Debe especificar al menos un pago");
       }

       for (AplicarPagoDTO aplicarPagoDTO : pagoClientesDto.getAplicarPagoDTO()) {
        
       }
    }

}
