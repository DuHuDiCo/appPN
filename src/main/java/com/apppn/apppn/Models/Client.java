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

@Entity
@Table(name = "CLIENT")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENT")
    private Long idClient;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "LASTNAME", length = 100)
    private String lastname;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "ENABLED")
    private Boolean enabled;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProductoCompra> productoCompras = new ArrayList<>();


    public Client() {
    }


    public Long getIdClient() {
        return idClient;
    }


    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public List<ProductoCompra> getProductoCompras() {
        return productoCompras;
    }


    public void setProductoCompras(List<ProductoCompra> productoCompras) {
        this.productoCompras = productoCompras;
    }


    public Boolean getEnabled() {
        return enabled;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    

}
