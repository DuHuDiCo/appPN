package com.apppn.apppn.DTO.Request;

import java.util.Date;

public class AplicarPagoDTO {

    private Long idPlanPago;
    private Long valor;
    private Long idFacturacion;
    private Date fechaPago;

    

    public AplicarPagoDTO() {
    }


    public Long getIdPlanPago() {
        return idPlanPago;
    }


    public void setIdPlanPago(Long idPlanPago) {
        this.idPlanPago = idPlanPago;
    }


    public Long getValor() {
        return valor;
    }


    public void setValor(Long valor) {
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
