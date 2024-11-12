package com.apppn.apppn.DTO.Response;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apppn.apppn.Models.Role;
import com.apppn.apppn.Models.UserRoles;



public class AuthenticationResponse {

    private String token;
    private String username;
    private Date lastSesion;
    private List<UserRoles> roles = new ArrayList<>();

    public AuthenticationResponse() {
    }

    

    public AuthenticationResponse(String token, String username, Date lastSesion, List<UserRoles> roles) {
        this.token = token;
        this.username = username;
        this.lastSesion = lastSesion;
        this.roles = roles;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getLastSesion() {
        return lastSesion;
    }

    public void setLastSesion(Date lastSesion) {
        this.lastSesion = lastSesion;
    }



    public List<UserRoles> getRoles() {
        return roles;
    }



    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }

}
