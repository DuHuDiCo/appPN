package com.apppn.apppn.Models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @ManyToMany
    @JoinTable(name = "INVENTORY_PRODUCTO_COMPRA", joinColumns = @JoinColumn(name = "INVENTORY_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCTO_COMPRA_ID"))
    private Set<ProductoCompra> productoCompras = new HashSet<>();


    public Inventory() {
    }


    public void agregarProducto(ProductoCompra productoCompra) {
        this.productoCompras.add(productoCompra);
        productoCompra.getInventories().add(this);
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Set<ProductoCompra> getProductoCompras() {
        return productoCompras;
    }


    public void setProductoCompras(Set<ProductoCompra> productoCompras) {
        this.productoCompras = productoCompras;
    }


    




}
