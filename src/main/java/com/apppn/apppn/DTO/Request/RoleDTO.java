package com.apppn.apppn.DTO.Request;

import java.util.List;

public class RoleDTO {


    private String role;
    private List<String> permissions;
    public RoleDTO() {
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public List<String> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    


}
