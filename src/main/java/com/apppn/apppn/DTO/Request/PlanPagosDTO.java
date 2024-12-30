
package com.apppn.apppn.DTO.Request;



public class PlanPagosDTO {


    private Integer perodicidad;
    private String fechaCorte;
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
    public String getFechaCorte() {
        return fechaCorte;
    }
    public void setFechaCorte(String fechaCorte) {
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
