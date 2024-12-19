package com.apppn.apppn.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TIPO_PAGO")
public class TipoPago {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PAGO")
    private Long idTipoPago;


    @Column(name = "NOMBRE_TIPO_PAGO")
    private String nombreTipoPago;


    @OneToMany(mappedBy = "tipoPago", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("tipoPago")
    private List<PagoClientes> pagoClientes = new ArrayList<>();

    public TipoPago() {
    }

    public Long getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Long idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getNombreTipoPago() {
        return nombreTipoPago;
    }

    public void setNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
    }   


}
