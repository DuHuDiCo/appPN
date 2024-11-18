package com.apppn.apppn.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAGO")
public class Pago {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGO")
    private Long idPago;


    @Column(name = "FECHA_PAGO")
    private String fechaPago;


    @ManyToOne
    @Column(name = "ARCHIVO_ID")
    private Archivos archivos;



    @ManyToOne
    @Column(name = "USER_ID")
    private User user;

    @Column(name = "TOTAL_PAGO")
    private Double totalPago;

    public Pago() {
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Archivos getArchivos() {
        return archivos;
    }

    public void setArchivos(Archivos archivos) {
        this.archivos = archivos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }







}
