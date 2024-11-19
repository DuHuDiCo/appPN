package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.UserDTO;




public interface UserService {


    public ResponseEntity<?> saveUser(UserDTO usuario);

    public ResponseEntity<?> getUserByEmail(String username);

    public ResponseEntity<?> getUsers();

    public ResponseEntity<?> editUser(Long id, UserDTO user);

    public ResponseEntity<?> getUserByNameOrLastName(String name, String lastName);

}
