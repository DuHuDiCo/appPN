package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturacionDTO {


    private Long idInventario;

    private Integer perodicidad;
    private Date fechaCorte;
    private Integer cuotas;
    private Double valorCuota;

    private List<FacturacionProductosDTO> productos = new ArrayList<>();


    public FacturacionDTO() {
    }


    public Long getIdInventario() {
        return idInventario;
    }


    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }


    public List<FacturacionProductosDTO> getProductos() {
        return productos;
    }


    public void setProductos(List<FacturacionProductosDTO> productos) {
        this.productos = productos;
    }


    public Integer getPerodicidad() {
        return perodicidad;
    }


    public void setPerodicidad(Integer perodicidad) {
        this.perodicidad = perodicidad;
    }


    public Date getFechaCorte() {
        return fechaCorte;
    }


    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }


    public Integer getCuotas() {
        return cuotas;
    }


    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }


    public Double getValorCuota() {
        return valorCuota;
    }


    public void setValorCuota(Double valorCuota) {
        this.valorCuota = valorCuota;
    }


  
    

    


}
