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
@Table(name = "LIQUIDACION")
public class Liquidacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "ID_LIQUIDACION")
    private Long idLiquidacion;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_LIQUIDACION")
    private Date fecha;


    @Column(name = "VALOR_VENTA")
    private Double valorVenta;

    @Column(name = "VALOR_LIQUIDADO")
    private Double valorLiquidado;


    @ManyToOne
    @JoinColumn(name = "USUARIO_VENDEDOR_ID")
    private User vendedor;

    @OneToMany(mappedBy = "liquidacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("liquidacion")
    private List<ProductoCompraFacturacion> productoCompraFacturacion = new ArrayList<>();

    public Liquidacion() {
    }

    public void agregarProductoCompraFacturacion(ProductoCompraFacturacion productoCompraFacturacion) {
        this.productoCompraFacturacion.add(productoCompraFacturacion);
        productoCompraFacturacion.setLiquidacion(this);
    }

    public Long getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(Long idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Double getValorLiquidado() {
        return valorLiquidado;
    }

    public void setValorLiquidado(Double valorLiquidado) {
        this.valorLiquidado = valorLiquidado;
    }

    public User getVendedor() {
        return vendedor;
    }

    public void setVendedor(User vendedor) {
        this.vendedor = vendedor;
    }

    public List<ProductoCompraFacturacion> getProductoCompraFacturacion() {
        return productoCompraFacturacion;
    }

    public void setProductoCompraFacturacion(List<ProductoCompraFacturacion> productoCompraFacturacion) {
        this.productoCompraFacturacion = productoCompraFacturacion;
    }



    


}
