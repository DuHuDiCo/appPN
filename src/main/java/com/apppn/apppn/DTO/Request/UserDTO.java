package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long idUser;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Boolean isEnabled;
    
    private List<RoleDTO> roles = new ArrayList<>();
    
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
    public List<RoleDTO> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
    public Boolean getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
   


    
    
}
