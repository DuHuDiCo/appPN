package com.apppn.apppn.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TIPO_VENTA")
public class TipoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_VENTA")
    private Long idTipoVenta;


    @Column(name = "TIPO_VENTA", length = 100)
    private String tipoVenta;

    @OneToMany(mappedBy = "tipoVenta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"tipoVenta"})
    private List<ProductoCompraFacturacion> productoCompraFacturacions = new ArrayList<>();


    public TipoVenta() {
    }


    public Long getIdTipoVenta() {
        return idTipoVenta;
    }


    public void setIdTipoVenta(Long idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }


    public String getTipoVenta() {
        return tipoVenta;
    }


    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }


    public List<ProductoCompraFacturacion> getProductoCompraFacturacions() {
        return productoCompraFacturacions;
    }


    public void setProductoCompraFacturacions(List<ProductoCompraFacturacion> productoCompraFacturacions) {
        this.productoCompraFacturacions = productoCompraFacturacions;
    }


   


}
