package com.apppn.apppn.ServiceImpl;


import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.RoleDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Permission;
import com.apppn.apppn.Models.Role;
import com.apppn.apppn.Repository.PermissionRepository;
import com.apppn.apppn.Repository.RolesRepository;
import com.apppn.apppn.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {


    private final RolesRepository roleRepository;
    private final PermissionRepository permissionRepository;


    

    public RoleServiceImpl(RolesRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public ResponseEntity<?> saveRole(RoleDTO roleDTO) {
        Role role = roleRepository.findByRole((String)roleDTO.getRole());
        if (Objects.nonNull(role)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Role already exists"));
        }

        role = new Role();
        role.setRole((String)roleDTO.getRole());
        roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @Override
    public ResponseEntity<?> getRoleById(Long idRole) {
        Role role = roleRepository.findById(idRole).orElse(null);
        if (Objects.isNull(role)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Role not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @Override
    public ResponseEntity<?> getPermissionById(Long idPermission) {
        Permission permission = permissionRepository.findById(idPermission).orElse(null);
        if (Objects.isNull(permission)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Permission not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(permission);
    }

}
