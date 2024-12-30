package com.apppn.apppn.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "FACTURACION")
public class Facturacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FACTURACION")
    private Long idFacturacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "TOTAL_FACTURACION")
    private Double totalFacturacion;

  
    
   
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "facturacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("facturacion")
    private List<ProductoCompraFacturacion> productoCompraFacturacion = new ArrayList<>();

    @ManyToMany(mappedBy = "facturaciones")
    @JsonIgnoreProperties("facturaciones")
    private Set<Inventory> inventories = new HashSet<>();

   

    @OneToMany(mappedBy = "facturacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("facturacion")
    private List<PlanPagos> planPagos = new ArrayList<>();
    
    @OneToMany(mappedBy = "facturacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("facturacion")
    List<PagoClientes> pagoClientes = new ArrayList<>();

    
    public Facturacion() {
    }


    public void agregarPagoClientes(PagoClientes pagoClientes) {
        pagoClientes.setFacturacion(this);
        pagoClientes.setFechaPago(fecha);
        
    }

    public void agregarProductoCompraFacturacion(ProductoCompraFacturacion compraFacturacion) {
        productoCompraFacturacion.add(compraFacturacion);
        compraFacturacion.setFacturacion(this);
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

    public List<ProductoCompraFacturacion> getProductoCompraFacturacion() {
        return productoCompraFacturacion;
    }

    public void setProductoCompraFacturacion(List<ProductoCompraFacturacion> productoCompraFacturacion) {
        this.productoCompraFacturacion = productoCompraFacturacion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }


    public List<PagoClientes> getPagoClientes() {
        return pagoClientes;
    }

    public void setPagoClientes(List<PagoClientes> pagoClientes) {
        this.pagoClientes = pagoClientes;
    }


    public List<PlanPagos> getPlanPagos() {
        return planPagos;
    }


    public void setPlanPagos(List<PlanPagos> planPagos) {
        this.planPagos = planPagos;
    }




    

}
