package com.apppn.apppn.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/google")
public class AuthenticationController {

    // private final GoogleAuthenticationService googleAuthenticationService;
    // private final AuthenticationService  authenticationService;

   
    // public AuthenticationController(GoogleAuthenticationService googleAuthenticationService,
    //         AuthenticationService authenticationService) {
    //     this.googleAuthenticationService = googleAuthenticationService;
    //     this.authenticationService = authenticationService;
    // }

    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
    //         @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    //         @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    // })
    // @GetMapping("/callback")
    // public ResponseEntity<?> googleCallBack( @RequestParam("code") String code,
    //         @RequestParam("scope") String scope)
    //         throws IOException, GeneralSecurityException {
    //     return googleAuthenticationService.googleCallBack(code, Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
    //                                     "https://www.googleapis.com/auth/userinfo.email",
    //                                     "openid"));
    // }

    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RedirectView.class)))
    // })
    // @GetMapping(value = "/authorize")
    // public RedirectView getGoogleAuthentication() {
    //     return googleAuthenticationService.getGoogleAuthentication();
    // }

    // @PostMapping("/movil")
    // public ResponseEntity<?> verifyTokenAndGetUserProfile(@RequestBody Map<String, String> request) throws GeneralSecurityException, IOException {
    //     return googleAuthenticationService.verifyTokenAndGetUserProfile(request.get("access_token"));
    // }


    
    // @PostMapping("/")
    // public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest ){
    //     return authenticationService.login(authenticationRequest);
    // }

}
