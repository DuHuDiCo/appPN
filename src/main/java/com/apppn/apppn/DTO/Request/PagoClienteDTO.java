package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.List;

public class PagoClienteDTO {

    private Double valor;
    private String numeroRecibo;
    private String comprobante;
    private String tipoPago;
    private List<AplicarPagoDTO> aplicarPagoDTO = new ArrayList<>();



    public PagoClienteDTO() {
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getNumeroRecibo() {
        return numeroRecibo;
    }
    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }
    public String getComprobante() {
        return comprobante;
    }
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }
    public String getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public List<AplicarPagoDTO> getAplicarPagoDTO() {
        return aplicarPagoDTO;
    }
    public void setAplicarPagoDTO(List<AplicarPagoDTO> aplicarPagoDTO) {
        this.aplicarPagoDTO = aplicarPagoDTO;
    }

    

}
