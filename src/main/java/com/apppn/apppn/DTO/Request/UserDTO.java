package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long idUser;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private List<Long> roles = new ArrayList<>();
    private List<Long> permissions = new ArrayList<>();
    public UserDTO() {
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
    public List<Long> getRoles() {
        return roles;
    }
    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
    public List<Long> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Long> permissions) {
        this.permissions = permissions;
    }


    
    
}
