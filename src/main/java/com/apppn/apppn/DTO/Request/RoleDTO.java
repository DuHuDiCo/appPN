package com.apppn.apppn.DTO.Request;

import java.util.List;

public class RoleDTO {


    private Object role;
    private List<Object> permissions;


    public RoleDTO() {
    }

    
    

    public Object getRole() {
        return role;
    }





    public void setRole(Object role) {
        this.role = role;
    }




    public List<Object> getPermissions() {
        return permissions;
    }




    public void setPermissions(List<Object> permissions) {
        this.permissions = permissions;
    }

    


}
