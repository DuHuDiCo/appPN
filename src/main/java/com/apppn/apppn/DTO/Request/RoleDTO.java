package com.apppn.apppn.DTO.Request;

import java.util.List;

public class RoleDTO {


    private Long role;
    private List<Long> permissions;
    private String rolename;

    public RoleDTO() {
    }

    
    

    public Long getRole() {
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




    public String getRolename() {
        return rolename;
    }




    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    


}
