package com.apppn.apppn.Models;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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


    @Column(name = "FLETE")
    private Double flete;

    @ManyToOne
    @JoinColumn(name = "COMPRA_ID")
    @JsonIgnore
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    private Producto producto;    

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    //facturacion
    @OneToMany(mappedBy = "productoCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("productoCompra")
    private List<ProductoCompraFacturacion> productoCompraFacturacion = new ArrayList<>();


    
    
   


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



    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


  


 


    public Double getFlete() {
        return flete;
    }


    public void setFlete(Double flete) {
        this.flete = flete;
    }


    public List<ProductoCompraFacturacion> getProductoCompraFacturacion() {
        return productoCompraFacturacion;
    }


    public void setProductoCompraFacturacion(List<ProductoCompraFacturacion> productoCompraFacturacion) {
        this.productoCompraFacturacion = productoCompraFacturacion;
    }




}
