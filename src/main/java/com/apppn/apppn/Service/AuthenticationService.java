package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.apppn.apppn.DTO.Request.AuthenticationRequest;



public interface AuthenticationService {


    public ResponseEntity<?> authenticateUser(OAuth2AuthenticationToken authentication);


    public ResponseEntity<?> login(AuthenticationRequest authenticationRequest);

}
