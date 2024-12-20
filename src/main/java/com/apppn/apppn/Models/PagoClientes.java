package com.apppn.apppn.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PAGO_CLIENTES")
public class PagoClientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGO_CLIENTE")
    private Long idPagoCliente;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_PAGO")
    private Date fechaPago;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "NUMERO_RECIBO")
    private String numeroRecibo;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PAGO")
    @JsonIgnoreProperties("pagoClientes")
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "COMPROBANTE_ID")
    @JsonIgnoreProperties("pagoClientes")
    private Archivos archivos;

    @ManyToOne
    @JoinColumn(name = "FACTURACION_ID")
    @JsonIgnoreProperties("pagoClientes")
    private Facturacion facturacion;

    public PagoClientes() {
    }

    public Long getIdPagoCliente() {
        return idPagoCliente;
    }

    public void setIdPagoCliente(Long idPagoCliente) {
        this.idPagoCliente = idPagoCliente;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Archivos getArchivos() {
        return archivos;
    }

    public void setArchivos(Archivos archivos) {
        this.archivos = archivos;
    }

    public Facturacion getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }

    


}
