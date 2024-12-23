package com.apppn.apppn.DTO.Request;

import java.util.Date;

public class AplicarPagoDTO {

    private Double valor;
    private Long idFacturacion;
    private Date fechaPago;

    

    public AplicarPagoDTO() {
    }


   

    public Double getValor() {
        return valor;
    }


    public void setValor(Double valor) {
        this.valor = valor;
    }


    public Long getIdFacturacion() {
        return idFacturacion;
    }


    public void setIdFacturacion(Long idFacturacion) {
        this.idFacturacion = idFacturacion;
    }


    public Date getFechaPago() {
        return fechaPago;
    }


    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    

    
}
