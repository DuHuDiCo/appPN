package com.apppn.apppn.Controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.apppn.apppn.DTO.Response.AuthenticationResponse;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Service.AuthenticationService;
import com.apppn.apppn.Service.GoogleAuthenticationService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/google")
public class AuthenticationController {

    private final GoogleAuthenticationService googleAuthenticationService;

    public AuthenticationController(GoogleAuthenticationService googleAuthenticationService) {
        this.googleAuthenticationService = googleAuthenticationService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/callback")
    public RedirectView googleCallBack(@RequestParam("state") String state, @RequestParam("code") String code,
            @RequestParam("scope") String scope)
            throws IOException, GeneralSecurityException {
        return googleAuthenticationService.googleCallBack(code, state);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RedirectView.class)))
    })
    @GetMapping(value = "/authorize")
    public RedirectView getGoogleAuthentication() {
        return googleAuthenticationService.getGoogleAuthentication();
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

}
