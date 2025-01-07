package com.apppn.apppn.DTO.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apppn.apppn.Models.PlanPagos;

public class FacturacionResponse {

    private Long idFacturacion;
    private Date fecha;

    private List<PlanPagos> planPagos = new ArrayList<>();

    public FacturacionResponse() {
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

    public List<PlanPagos> getPlanPagos() {
        return planPagos;
    }

    public void setPlanPagos(List<PlanPagos> planPagos) {
        this.planPagos = planPagos;
    }


    

}
