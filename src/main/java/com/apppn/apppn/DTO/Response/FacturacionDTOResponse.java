package com.apppn.apppn.DTO.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.PlanPagos;

public class FacturacionDTOResponse {

    private Double valor;
    private Date fecha;
    List<Facturacion> facturacion = new ArrayList<>();
    public FacturacionDTOResponse() {
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public List<Facturacion> getFacturacion() {
        return facturacion;
    }
    public void setFacturacion(List<Facturacion> facturacion) {
        this.facturacion = facturacion;
    }

    

}
