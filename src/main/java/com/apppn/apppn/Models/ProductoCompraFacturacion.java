package com.apppn.apppn.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PRODUCTO_COMPRA_FACTURACION")
public class ProductoCompraFacturacion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO_COMPRA_FACTURACION")
    private Long idProductoCompraFacturacion;


    @ManyToOne
    @JoinColumn(name = "PRODUCTO_COMPRA_ID")
    @JsonIgnoreProperties("productoCompraFacturacions")
    private ProductoCompraInventory productoCompraInventory;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;


    @ManyToOne
    @JoinColumn(name = "FACTURACION_ID")
    private Facturacion facturacion;


    @Column(name = "VALOR_VENTA")
    private Double valorVenta;

    @Column(name = "CANTIDAD")
    private Integer cantidad;


    @Column(name = "DESCUENTO_PAGO_INICIAL")
    private Double descuentoPagoInicial;


    @ManyToOne
    @JoinColumn(name = "LIQUIDACION_ID")
    @JsonIgnoreProperties("productoCompraFacturacion")
    private Liquidacion liquidacion;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_COMPRA_ID")
    @JsonIgnoreProperties("productoCompraFacturacion")
    private ProductoCompra productoCompra;


    public ProductoCompraFacturacion() {
    }

    public Long getIdProductoCompraFacturacion() {
        return idProductoCompraFacturacion;
    }

    public void setIdProductoCompraFacturacion(Long idProductoCompraFacturacion) {
        this.idProductoCompraFacturacion = idProductoCompraFacturacion;
    }

  

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Facturacion getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }

    public Double getDescuentoPagoInicial() {
        return descuentoPagoInicial;
    }

    public void setDescuentoPagoInicial(Double descuentoPagoInicial) {
        this.descuentoPagoInicial = descuentoPagoInicial;
    }

    

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoCompraInventory getProductoCompraInventory() {
        return productoCompraInventory;
    }

    public void setProductoCompraInventory(ProductoCompraInventory productoCompraInventory) {
        this.productoCompraInventory = productoCompraInventory;
    }

    public ProductoCompra getProductoCompra() {
        return productoCompra;
    }

    public void setProductoCompra(ProductoCompra productoCompra) {
        this.productoCompra = productoCompra;
    }


    

}
