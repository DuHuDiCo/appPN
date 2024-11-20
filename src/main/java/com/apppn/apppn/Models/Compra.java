package com.apppn.apppn.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "COMPRA")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMPRA")
    private Long idCompra;


    @Column(name = "MONTO")
    private Double monto;

    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "PROVEEDOR_ID")
    private Proveedor proveedor;

    @Column(name = "IS_PAGO")
    private Boolean isPago;

  
    @Column(name = "TOTAL_PAGAR")
    private Double totalPagar;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PAGO_ID", referencedColumnName = "ID_PAGO")
    private Pago pago;
    

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoCompra> productoCompras = new ArrayList<>();

    public Compra() {
    }


    public void agregarProducto(ProductoCompra productoCompra) {
        this.productoCompras.add(productoCompra);
        productoCompra.setCompra(this);
    }

    public Long getIdCompra() {
        return idCompra;
    }


    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }


    public Double getMonto() {
        return monto;
    }


    public void setMonto(Double monto) {
        this.monto = monto;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Proveedor getProveedor() {
        return proveedor;
    }


    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }


    public List<ProductoCompra> getProductoCompras() {
        return productoCompras;
    }


    public void setProductoCompras(List<ProductoCompra> productoCompras) {
        this.productoCompras = productoCompras;
    }


    public Boolean getIsPago() {
        return isPago;
    }


    public void setIsPago(Boolean isPago) {
        this.isPago = isPago;
    }


  


    public Double getTotalPagar() {
        return totalPagar;
    }


    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }


    public Pago getPago() {
        return pago;
    }


    public void setPago(Pago pago) {
        this.pago = pago;
    }



    



}
