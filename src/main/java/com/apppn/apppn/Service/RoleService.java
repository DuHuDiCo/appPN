package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.RoleDTO;

public interface RoleService {

    public ResponseEntity<?> saveRole(RoleDTO roleDTO);

    public ResponseEntity<?> getRoleById(Long idRole);

    public ResponseEntity<?> getPermissionById(Long idPermission);

}
