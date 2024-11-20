
package com.apppn.apppn.Models;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apppn.apppn.Security.Security.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "USER")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "LASTNAME", length = 100)
    private String lastname;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "PASSWORD", columnDefinition = "TEXT")
    private String password;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "IS_ENABLED")
    private Boolean enabled;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<UserRoles> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoCompra> productoCompras = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pago> pagos = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Client> clients = new ArrayList<>();



    

    public User() {
    }

    public void agregarCliente(Client client) {
        this.clients.add(client);
        client.setUser(this);
    }


    public void agregarRole(UserRoles userRoles) {
        this.userRoles.add(userRoles);
        userRoles.setUser(this);
    }


    public Long getIdUser() {
        return idUser;
    }


    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Date getDateCreated() {
        return dateCreated;
    }


    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    public List<UserRoles> getUserRoles() {
        return userRoles;
    }


    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         Set<Authority> autoridades = new HashSet<>();
        this.userRoles.forEach(rol -> {
            autoridades.add(new Authority(Objects.isNull(rol.getRole())?"N/A":rol.getRole().getRole()));
        });
        return autoridades;
    }


    @Override
    public String getUsername() {
       return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.enabled = isEnabled;
    }


    public List<ProductoCompra> getProductoCompras() {
        return productoCompras;
    }


    public void setProductoCompras(List<ProductoCompra> productoCompras) {
        this.productoCompras = productoCompras;
    }


    public List<Pago> getPagos() {
        return pagos;
    }


    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }


    public List<Client> getClients() {
        return clients;
    }


    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


    
    

}
