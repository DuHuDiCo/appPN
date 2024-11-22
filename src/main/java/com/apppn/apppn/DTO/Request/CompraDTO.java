package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.List;


public class CompraDTO {


    private Double monto;


    private Long idProveedor;

    private List<CompraProductoDTO> productos = new ArrayList<>();

    
    private Double totalCompra;
    private Double totalPagar;
    

    public CompraDTO() {
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public List<CompraProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<CompraProductoDTO> productos) {
        this.productos = productos;
    }


    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    


}
