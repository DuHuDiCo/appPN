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
@Table(name = "ROLE")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROLE")
    private Long idRole;

    @Column(name = "ROLE", length = 100)
    private String role;


    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserRoles> userRoles = new ArrayList<>();


    public Role() {
    }


    public Long getIdRole() {
        return idRole;
    }


    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public List<UserRoles> getUserRoles() {
        return userRoles;
    }


    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }


}
