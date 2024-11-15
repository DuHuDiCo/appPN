package com.apppn.apppn.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO")
    private Long idProducto;

    @Column(name = "NOMBRE", length = 100)
    private String producto;

    
    @Column(name = "DESCRIPCION", length = 1000)
    private String descripcion;

    @Column(name = "IMAGEN", length = 1000)
    private String imagen;


    @ManyToOne
    @JoinColumn(name = "CLASIFICACION_PRODUCTO_ID")
    private ClasificacionProducto clasificacionProducto;

    public Producto() {
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ClasificacionProducto getClasificacionProducto() {
        return clasificacionProducto;
    }

    public void setClasificacionProducto(ClasificacionProducto clasificacionProducto) {
        this.clasificacionProducto = clasificacionProducto;
    }


    
  

}
