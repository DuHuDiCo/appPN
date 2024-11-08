package com.apppn.apppn.ServiceImpl;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.RoleDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Role;
import com.apppn.apppn.Repository.RolesRepository;
import com.apppn.apppn.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {


    private final RolesRepository roleRepository;

    public RoleServiceImpl(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<?> saveRole(RoleDTO roleDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveRole'");
    }

    @Override
    public ResponseEntity<?> getRoleById(Long idRole) {
        Role role = roleRepository.findById(idRole).orElse(null);
        if (Objects.isNull(role)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Role not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

}
