package com.apppn.apppn.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_IMAGE", joinColumns = @JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns = @JoinColumn(name = "IMAGE_ID"))
    private Set<Archivos> imagenes = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "CLASIFICACION_PRODUCTO_ID")
    private ClasificacionProducto clasificacionProducto;

    public Producto() {
    }

    public void agregarImagen(Archivos archivos) {
        imagenes.add(archivos);
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

    

    public ClasificacionProducto getClasificacionProducto() {
        return clasificacionProducto;
    }

    public void setClasificacionProducto(ClasificacionProducto clasificacionProducto) {
        this.clasificacionProducto = clasificacionProducto;
    }

    public Set<Archivos> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Archivos> imagenes) {
        this.imagenes = imagenes;
    }


    
  

}
