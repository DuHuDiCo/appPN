package com.apppn.apppn.DTO.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apppn.apppn.Models.PlanPagos;

public class FacturacionDTOResponse {

    private Long idFacturacion;
    private Date fecha;
    private Double totalFacturacion;
    private Integer perodicidad;
    private Date fechaCorte;
    List<PlanPagos> planPagos = new ArrayList<>();

    public FacturacionDTOResponse() {
    }

    public Long getIdFacturacion() {
        return idFacturacion;
    }

    public void setIdFacturacion(Long idFacturacion) {
        this.idFacturacion = idFacturacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotalFacturacion() {
        return totalFacturacion;
    }

    public void setTotalFacturacion(Double totalFacturacion) {
        this.totalFacturacion = totalFacturacion;
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

    public List<PlanPagos> getPlanPagos() {
        return planPagos;
    }

    public void setPlanPagos(List<PlanPagos> planPagos) {
        this.planPagos = planPagos;
    }

}
