package com.apppn.apppn.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.DTO.Response.AuthenticationResponse;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Service.AuthenticationService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/oauth2")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }




    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/callback")
    public ResponseEntity<?> authenticateUser(OAuth2AuthenticationToken authentication) {
        return authenticationService.authenticateUser(authentication);
    }

    @GetMapping("/login")
    public String login(){
        return "Login";
    }


}
