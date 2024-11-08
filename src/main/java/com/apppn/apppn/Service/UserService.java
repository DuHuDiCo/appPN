package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.UserDTO;




public interface UserService {


    public ResponseEntity<?> saveUser(UserDTO usuario);

}
