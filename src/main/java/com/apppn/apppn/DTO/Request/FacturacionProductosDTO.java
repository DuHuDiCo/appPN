package com.apppn.apppn.DTO.Request;

public class FacturacionProductosDTO {


    private Long idProductoCompra;
    private Long idCliente;
    private Integer cantidad;
    private Double valorVenta;
    private Double descuentoPagoInicial;
    




    public FacturacionProductosDTO() {
    }
    public Long getIdProductoCompra() {
        return idProductoCompra;
    }
    public void setIdProductoCompra(Long idProductoCompra) {
        this.idProductoCompra = idProductoCompra;
    }
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public Double getValorVenta() {
        return valorVenta;
    }
    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
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


    

}
