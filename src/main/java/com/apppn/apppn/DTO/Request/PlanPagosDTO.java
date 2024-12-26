
package com.apppn.apppn.DTO.Request;

import java.util.Date;

public class PlanPagosDTO {


    private Integer perodicidad;
    private Date fechaCorte;
    private Integer cuotas;
    private Double valorCuota;
    public PlanPagosDTO() {
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
