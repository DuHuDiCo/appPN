package com.apppn.apppn.DTO.Request;

import java.util.List;

public class RoleDTO {


    private Long role;
    private List<Long> permissions;


    public RoleDTO() {
    }

    
    

    public Object getRole() {
        return role;
    }





    public void setRole(Long role) {
        this.role = role;
    }




    public List<Long> getPermissions() {
        return permissions;
    }




    public void setPermissions(List<Long> permissions) {
        this.permissions = permissions;
    }

    


}
