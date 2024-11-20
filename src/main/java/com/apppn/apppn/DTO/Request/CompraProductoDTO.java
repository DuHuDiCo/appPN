package com.apppn.apppn.DTO.Request;

public class CompraProductoDTO {

    private Integer cantidad;
    private Double costo;
    private Long idProducto;
    private Long idTipoVenta;

    private Boolean isDescuentoInicial;
    



    public CompraProductoDTO() {
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
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Long getIdTipoVenta() {
        return idTipoVenta;
    }
    public void setIdTipoVenta(Long idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }
    public Boolean getIsDescuentoInicial() {
        return isDescuentoInicial;
    }
    public void setIsDescuentoInicial(Boolean isDescuentoInicial) {
        this.isDescuentoInicial = isDescuentoInicial;
    }
   

    

}
