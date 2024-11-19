package com.apppn.apppn.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUCTO_COMPRA")
public class ProductoCompra {


     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO_COMPRA")
    private Long idProductoCompra;


    @Column(name = "CANTIDAD")
    private Integer cantidad;


    @Column(name = "COSTO")
    private Double costo;

    @ManyToOne
    @JoinColumn(name = "COMPRA_ID")
    @JsonIgnore
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    private Producto producto;


    @ManyToOne
    @JoinColumn(name = "TIPO_VENTA_ID")
    private TipoVenta tipoVenta;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    
    
    @ManyToMany(mappedBy = "productoCompras")
    @JsonIgnore
    private Set<Inventory> inventories = new HashSet<>();


    public ProductoCompra() {
    }


    public Long getIdProductoCompra() {
        return idProductoCompra;
    }


    public void setIdProductoCompra(Long idProductoCompra) {
        this.idProductoCompra = idProductoCompra;
    }


    public Integer getCantidad() {
        return cantidad;
    }


    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public Double getCosto() {
        return costo;
    }


    public void setCosto(Double costo) {
        this.costo = costo;
    }


    public Compra getCompra() {
        return compra;
    }


    public void setCompra(Compra compra) {
        this.compra = compra;
    }


    public Producto getProducto() {
        return producto;
    }


    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public TipoVenta getTipoVenta() {
        return tipoVenta;
    }


    public void setTipoVenta(TipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
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


    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }


    




}
