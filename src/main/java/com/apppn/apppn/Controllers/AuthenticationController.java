package com.apppn.apppn.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.DTO.Request.AuthenticationRequest;
import com.apppn.apppn.Service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/google")
public class AuthenticationController {

    
    private final AuthenticationService  authenticationService;

   
   
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    

    
    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest ){
        return authenticationService.login(authenticationRequest);
    }

}
