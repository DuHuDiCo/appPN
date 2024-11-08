package com.apppn.apppn.ServiceImpl;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.UserDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Role;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.UserRepository;
import com.apppn.apppn.Service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository usuarioRepository;
    

    public UserServiceImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<?> saveUser(UserDTO userDTO) {
        User newUser = usuarioRepository.findByEmail(userDTO.getEmail());
        if (Objects.isNull(newUser)) {
            newUser = new User();
            newUser.setEmail(userDTO.getEmail());
            newUser.setName(userDTO.getName());
            newUser.setLastname(userDTO.getLastname());
            newUser.setPassword(userDTO.getPassword());



            if(CollectionUtils.isEmpty(userDTO.getRoles()) || CollectionUtils.isEmpty(userDTO.getPermissions())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Array of type:<Roles> is Empty"));
            }

            for (Long roleLong : userDTO.getRoles()) {
                
            }
            
        }
        return null;
    }

}
