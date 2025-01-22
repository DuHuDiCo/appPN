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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USER_ROLES")
public class UserRoles {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER_ROLE")
    private Long idUserRol;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

   
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES_PERMISSION", joinColumns = @JoinColumn(name = "USER_ROL_ID"), inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"))
    private Set<Permission> permission = new HashSet<>();

    public UserRoles() {
    }

    public void agregarPermission(Permission permission) {
        this.permission.add(permission);
        permission.getUserRoles().add(this);
    }



    public Long getIdUserRol() {
        return idUserRol;
    }

    public void setIdUserRol(Long idUserRol) {
        this.idUserRol = idUserRol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }




    public Set<Permission> getPermission() {
        return permission;
    }




    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }


    
}
