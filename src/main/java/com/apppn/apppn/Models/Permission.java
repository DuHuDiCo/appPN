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
@Table(name = "PERMISSION")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERMISSION")
    private Long idPermission;

    @Column(name = "PERMISSION", length = 50)
    private String permission;


    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserRoles> userRoles = new ArrayList<>();


    public Permission() {
    }


    public void agregarUserRoles(UserRoles userRoles) {
        this.userRoles.add(userRoles);
        userRoles.setPermission(this);
    }
    

    public Long getIdPermission() {
        return idPermission;
    }


    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }


    public String getPermission() {
        return permission;
    }


    public void setPermission(String permission) {
        this.permission = permission;
    }


    public List<UserRoles> getUserRoles() {
        return userRoles;
    }


    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }


    

}
