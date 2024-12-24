package com.apppn.apppn.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PLAN_PAGOS")
public class PlanPagos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLAN_PAGOS")
    private Long idPlanPagos;

    @Column(name = "VALOR_CUOTA")
    private Double valorCuota;

    @Column(name = "SALDO")
    private Double saldo;

    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;

    @ManyToOne
    @JoinColumn(name = "FACTURACION_ID")
    @JsonIgnoreProperties("planPagos")
    private Facturacion facturacion;
    

    public PlanPagos() {
    }

    public Long getIdPlanPagos() {
        return idPlanPagos;
    }

    public void setIdPlanPagos(Long idPlanPagos) {
        this.idPlanPagos = idPlanPagos;
    }

    public Double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(Double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Facturacion getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }



    








}
