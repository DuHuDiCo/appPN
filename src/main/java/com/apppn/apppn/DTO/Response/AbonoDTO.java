package com.apppn.apppn.DTO.Response;

import java.util.ArrayList;
import java.util.List;

public class AbonoDTO {

    private Long idCliente;
   
    private List<CuotasRequest> cuotas = new ArrayList<>();

    public AbonoDTO() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

   

    public List<CuotasRequest> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<CuotasRequest> cuotas) {
        this.cuotas = cuotas;
    }

}
