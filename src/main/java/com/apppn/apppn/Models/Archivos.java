package com.apppn.apppn.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ARCHIVOS", indexes = {
        @Index(columnList = "ID_ARCHIVO"),
        @Index(columnList = "FILENAME")
})
public class Archivos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARCHIVO")
    private Long idArchivo;

    @Column(name = "FILENAME", length = 100)
    private String filename;

    @Column(name = "RUTA")
    private String ruta;

    @Column(name = "SIZE")
    private Long size;

    @Column(name = "EXTENTION", length = 100)
    private String extention;

    @Column(name = "URL_PATH")
    private String urlPath;


    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToMany(mappedBy = "imagenes")
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

    @OneToMany(mappedBy = "archivos", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pago> pagos = new ArrayList<>();


    public Archivos() {
    }


    public Long getIdArchivo() {
        return idArchivo;
    }


    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }


    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String getRuta() {
        return ruta;
    }


    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


    public Long getSize() {
        return size;
    }


    public void setSize(Long size) {
        this.size = size;
    }


    public String getExtention() {
        return extention;
    }


    public void setExtention(String extention) {
        this.extention = extention;
    }


    public String getUrlPath() {
        return urlPath;
    }


    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }


    public Date getFechaCreacion() {
        return fechaCreacion;
    }


    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public Set<Producto> getProductos() {
        return productos;
    }


    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }


    public List<Pago> getPagos() {
        return pagos;
    }


    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }


    

}
