package com.apppn.apppn.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTO_COMPRA")
public class ProductoCompra {


     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO_COMPRA")
    private Long idProductoCompra;


    private Integer cantidad;

    private Double costo;

    private Compra compra;

    private Producto producto;


    private TipoVenta tipoVenta;




}
