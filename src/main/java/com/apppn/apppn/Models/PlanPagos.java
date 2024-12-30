package com.apppn.apppn.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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


    @Column(name = "VALOR")
    private Double valor;

    


    @ManyToOne
    @JoinColumn(name = "FACTURACION_ID")
    @JsonIgnoreProperties("planPagos")
    private Facturacion facturacion;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    @JsonIgnoreProperties({"planPagos", "productoCompraFacturacion"})
    private Client client;


    @OneToMany(mappedBy = "planPagos", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("planPagos")
    private List<Cuotas> cuotas = new ArrayList<>();


    public PlanPagos() {
    }

    public void agrgarCuotas(Cuotas cuotas){
        this.cuotas.add(cuotas);
        cuotas.setPlanPagos(this);
    }

    public Long getIdPlanPagos() {
        return idPlanPagos;
    }

    public void setIdPlanPagos(Long idPlanPagos) {
        this.idPlanPagos = idPlanPagos;
    }

   

    public Facturacion getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }

    public List<Cuotas> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuotas> cuotas) {
        this.cuotas = cuotas;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }



    








}
