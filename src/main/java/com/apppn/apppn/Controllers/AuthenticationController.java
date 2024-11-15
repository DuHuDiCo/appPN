package com.apppn.apppn.Controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.apppn.apppn.DTO.Request.AuthenticationRequest;
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
    private final AuthenticationService  authenticationService;

   
    public AuthenticationController(GoogleAuthenticationService googleAuthenticationService,
            AuthenticationService authenticationService) {
        this.googleAuthenticationService = googleAuthenticationService;
        this.authenticationService = authenticationService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/callback")
    public ResponseEntity<RedirectView> googleCallBack( @RequestParam("code") String code,
            @RequestParam("scope") String scope)
            throws IOException, GeneralSecurityException {
        return googleAuthenticationService.googleCallBack(code, Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
                                        "https://www.googleapis.com/auth/userinfo.email",
                                        "openid"));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RedirectView.class)))
    })
    @GetMapping(value = "/authorize")
    public RedirectView getGoogleAuthentication() {
        return googleAuthenticationService.getGoogleAuthentication();
    }

    @PostMapping("/movil")
    public ResponseEntity<?> verifyTokenAndGetUserProfile(@RequestBody Map<String, String> request) throws GeneralSecurityException, IOException {
        return googleAuthenticationService.verifyTokenAndGetUserProfile(request.get("access_token"));
    }


    
    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest ){
        return authenticationService.login(authenticationRequest);
    }

}
