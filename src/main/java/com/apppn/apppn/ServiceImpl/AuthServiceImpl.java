package com.apppn.apppn.ServiceImpl;


import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Response.AuthenticationResponse;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Security.Security.JwtUtils;
import com.apppn.apppn.Service.AuthenticationService;
import com.apppn.apppn.Service.UserService;

@Service
public class AuthServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    

    public AuthServiceImpl(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }



    
    @Override
    public ResponseEntity<?> authenticateUser(OAuth2AuthenticationToken authentication) {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        
        ResponseEntity<?> userValidate = userService.getUserByEmail(user.getAttribute("email"));
        if(userValidate.getStatusCode().equals(HttpStatus.BAD_REQUEST)){
            return userValidate;
        }

        User userDB = ( User) userValidate.getBody();
        if(Objects.isNull(userDB)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found, must register first"));
        }

        String token = jwtUtils.generateToken(userDB);

        return ResponseEntity.status(HttpStatus.OK)
        .body(new AuthenticationResponse(token, userDB.getUsername(), new Date(), userDB.getUserRoles()));

        


    }

}
