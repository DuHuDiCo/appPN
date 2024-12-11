package com.apppn.apppn.Models;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PRODUCTO_COMPRA_INVENTORY")
public class ProductoCompraInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO_COMPRA_INVENTORY")
    private Long idProductoCompraInventory;


    @ManyToOne
    @JoinColumn(name = "PRODUCTO_COMPRA_ID")
    private ProductoCompra productoCompra;

    @ManyToOne(cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "INVENTORY_ID")
    @JsonIgnoreProperties("productoCompras")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @OneToMany(mappedBy = "productoCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("productoCompra")
    private List<ProductoCompraInventory> productoCompraInventory = new ArrayList<>();

    public ProductoCompraInventory() {
    }

    public Long getIdProductoCompraInventory() {
        return idProductoCompraInventory;
    }

    public void setIdProductoCompraInventory(Long idProductoCompraInventory) {
        this.idProductoCompraInventory = idProductoCompraInventory;
    }

    public ProductoCompra getProductoCompra() {
        return productoCompra;
    }

    public void setProductoCompra(ProductoCompra productoCompra) {
        this.productoCompra = productoCompra;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    

}
