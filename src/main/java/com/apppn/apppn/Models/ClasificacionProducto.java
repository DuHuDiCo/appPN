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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLASIFICACION_PRODUCTO")
public class ClasificacionProducto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLASIFICACION_PRODUCTO")
    private Long idClasificacionProducto;

    @Column(name = "CLASIFICACION_PRODUCTO", length = 100)
    private String clasificacionProducto;


    @Column(name = "IS_FLETE_OBLIGATORIO")
    private Boolean isFleteObligatorio;

    @OneToMany(mappedBy = "clasificacionProducto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Producto> productos = new ArrayList<>();

    public ClasificacionProducto() {
    }

    public Long getIdClasificacionProducto() {
        return idClasificacionProducto;
    }

    public void setIdClasificacionProducto(Long idClasificacionProducto) {
        this.idClasificacionProducto = idClasificacionProducto;
    }

    public String getClasificacionProducto() {
        return clasificacionProducto;
    }

    public void setClasificacionProducto(String clasificacionProducto) {
        this.clasificacionProducto = clasificacionProducto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Boolean getIsFleteObligatorio() {
        return isFleteObligatorio;
    }

    public void setIsFleteObligatorio(Boolean isFleteObligatorio) {
        this.isFleteObligatorio = isFleteObligatorio;
    }

    

}
