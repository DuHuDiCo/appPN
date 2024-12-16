package com.apppn.apppn.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVENTORY")
    private Long idInventory;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_INVENTORY")
    private Date dateInventory;

    @Column(name = "TOTAL_INVENTORY_VALUE")
    private Double totalInventoryValue;

    @Column(name = "QUANTITY")
    private Integer quantity;


    @Column(name = "QUANTITY_SIN_FACTURACION")
    private Integer quantitySinFacturacion;
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "INVENTORY_FACTURACION", joinColumns = @JoinColumn(name = "INVENTORI_ID"), inverseJoinColumns = @JoinColumn(name = "FACTURACION_ID"))
    @JsonIgnoreProperties({"inventories", "productoCompraFacturacion"})
    private Set<Facturacion> facturaciones = new HashSet<>();

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("inventory")
    List<ProductoCompraInventory> productoCompras = new ArrayList<>();

    public Inventory() {
    }

    public void agregarProductoCompra(ProductoCompraInventory productoCompra) {
        this.productoCompras.add(productoCompra);
        productoCompra.setInventory(this);
    }

    public void agregarFacturacion(Facturacion facturacion) {
        this.facturaciones.add(facturacion);
        facturacion.getInventories().add(this);
    }


    public Long getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Long idInventory) {
        this.idInventory = idInventory;
    }

    public Date getDateInventory() {
        return dateInventory;
    }

    public void setDateInventory(Date dateInventory) {
        this.dateInventory = dateInventory;
    }

    public Double getTotalInventoryValue() {
        return totalInventoryValue;
    }

    public void setTotalInventoryValue(Double totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   


   

    public List<ProductoCompraInventory> getProductoCompras() {
        return productoCompras;
    }

    public void setProductoCompras(List<ProductoCompraInventory> productoCompras) {
        this.productoCompras = productoCompras;
    }

    public Integer getQuantitySinFacturacion() {
        return quantitySinFacturacion;
    }

    public void setQuantitySinFacturacion(Integer quantitySinFacturacion) {
        this.quantitySinFacturacion = quantitySinFacturacion;
    }

    public Set<Facturacion> getFacturaciones() {
        return facturaciones;
    }

    public void setFacturaciones(Set<Facturacion> facturaciones) {
        this.facturaciones = facturaciones;
    }

}
