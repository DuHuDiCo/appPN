package com.apppn.apppn.DTO.Request;

import java.util.Date;

public class AplicarPagoDTO {

    private Double valor;
    private Long idCliente;
    private Long idFacturacion;
    private String fechaPago;

    

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


    public String getFechaPago() {
        return fechaPago;
    }


    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }




    public Long getIdCliente() {
        return idCliente;
    }




    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    

    
}
