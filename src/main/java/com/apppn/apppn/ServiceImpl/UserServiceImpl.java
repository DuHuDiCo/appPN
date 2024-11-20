package com.apppn.apppn.ServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.RoleDTO;
import com.apppn.apppn.DTO.Request.UserDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Permission;
import com.apppn.apppn.Models.Role;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Models.UserRoles;

import com.apppn.apppn.Repository.UserRepository;
import com.apppn.apppn.Service.RoleService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository usuarioRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Functions functions;

    public UserServiceImpl(UserRepository usuarioRepository, RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder, Functions functions) {
        this.usuarioRepository = usuarioRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.functions = functions;
    }

    @SuppressWarnings("null")
    @Override
    public ResponseEntity<?> saveUser(UserDTO userDTO) {
        User newUser = usuarioRepository.findByEmail(userDTO.getEmail());
        if (Objects.isNull(newUser)) {
            newUser = new User();
            newUser.setEmail(userDTO.getEmail());
            newUser.setName(userDTO.getName());
            newUser.setLastname(userDTO.getLastname());
            newUser.setEnabled(true);
            if(Objects.nonNull(userDTO.getPassword())){
                newUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            }
            try {
                newUser.setDateCreated(functions.obtenerFechaYhora());
                
            } catch (ParseException e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error al obtener la fecha y hora"));
            }

            // if(CollectionUtils.isEmpty(userDTO.getRoles()) ){
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
            // ErrorResponse("Array of type:<Roles> is Empty"));
            // }

            for (RoleDTO roleDTO : userDTO.getRoles()) {

                UserRoles userRoles = new UserRoles();
                userRoles.setUser(newUser);

                if (roleDTO.getRole() instanceof String) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ErrorResponse("Rol is a instace of Long, must be Long"));
                }

                if (roleDTO.getRole() instanceof Long) {
                    ResponseEntity<?> role = roleService.getRoleById(((Long) roleDTO.getRole()));
                    if (role.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                        return role;
                    }

                    Role roleFound = (Role) role.getBody();
                    if (Objects.isNull(roleFound)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Role not found"));
                    }

                    roleFound.agregarUserRoles(userRoles);

                    if (!CollectionUtils.isEmpty(roleDTO.getPermissions())) {
                        for (Object permission : roleDTO.getPermissions()) {

                            if (permission instanceof String) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(new ErrorResponse("Rol is a instace of Long, must be Long"));
                            }

                            if (permission instanceof Long) {
                                ResponseEntity<?> permissionResponse = roleService.getPermissionById((Long) permission);
                                if (permissionResponse.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                                    return permissionResponse;
                                }

                                Permission permissionfound = (Permission) permissionResponse.getBody();
                                if (Objects.isNull(permissionfound)) {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                            .body(new ErrorResponse("Permission not found"));
                                }

                                permissionfound.agregarUserRoles(userRoles);
                            }

                        }
                    }

                }

                newUser.agregarRole(userRoles);

            }

            newUser = usuarioRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("User already exists"));
    }

    @Override
    public ResponseEntity<?> getUserByEmail(String username) {
        User user = usuarioRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("User not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    public ResponseEntity<?> getUsers() {
        List<User> users = usuarioRepository.findAll();
        if (CollectionUtils.isEmpty(users)) {
            users = new ArrayList<>();
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Override
    public ResponseEntity<?> editUser(Long id, UserDTO user) {
        User userEdit = usuarioRepository.findById(id).orElse(null);
        if (Objects.isNull(userEdit)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("User not found"));
        }
        userEdit.setEmail(user.getEmail());
        userEdit.setLastname(user.getLastname());
        userEdit.setName(user.getName());
        if(Objects.nonNull(user.getPassword())){
            userEdit.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userEdit.setEnabled(user.getEnable());


        if (!CollectionUtils.isEmpty(user.getRoles())) {
            userEdit.getUserRoles().clear();


            for (RoleDTO roleDTO : user.getRoles()) {

                UserRoles userRoles = new UserRoles();
                userRoles.setUser(userEdit);

                if (roleDTO.getRole() instanceof String) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ErrorResponse("Rol is a instace of Long, must be Long"));
                }

                if (roleDTO.getRole() instanceof Long) {
                    ResponseEntity<?> role = roleService.getRoleById(((Long) roleDTO.getRole()));
                    if (role.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                        return role;
                    }

                    Role roleFound = (Role) role.getBody();
                    if (Objects.isNull(roleFound)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Role not found"));
                    }

                    roleFound.agregarUserRoles(userRoles);

                    if (!CollectionUtils.isEmpty(roleDTO.getPermissions())) {
                        for (Object permission : roleDTO.getPermissions()) {

                            if (permission instanceof String) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(new ErrorResponse("Rol is a instace of Long, must be Long"));
                            }

                            if (permission instanceof Long) {
                                ResponseEntity<?> permissionResponse = roleService.getPermissionById((Long) permission);
                                if (permissionResponse.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                                    return permissionResponse;
                                }

                                Permission permissionfound = (Permission) permissionResponse.getBody();
                                if (Objects.isNull(permissionfound)) {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                            .body(new ErrorResponse("Permission not found"));
                                }

                                permissionfound.agregarUserRoles(userRoles);
                            }

                        }
                    }

                }

                userEdit.agregarRole(userRoles);

            }



        }

        userEdit = usuarioRepository.save(userEdit);
        return ResponseEntity.status(HttpStatus.OK).body(userEdit);

    }

    @Override
    public ResponseEntity<?> getUserByNameOrLastName(String dato) {
        String[] datos = dato.split(" ");
        List<User> user = usuarioRepository.findByNameOrLastname(datos[0], datos.length > 1 ? datos[1] : null);
        if (CollectionUtils.isEmpty(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
