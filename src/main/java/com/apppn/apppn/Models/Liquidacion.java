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





}
