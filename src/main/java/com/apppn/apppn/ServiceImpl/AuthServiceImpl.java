package com.apppn.apppn.ServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.AuthenticationRequest;
import com.apppn.apppn.DTO.Response.AuthenticationResponse;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Compra;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Security.Security.JwtUtils;
import com.apppn.apppn.Security.Security.UserDetailsServiceImple;
import com.apppn.apppn.Service.AuthenticationService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Service
public class AuthServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImple userDetailsServiceImple;
    private final Functions functions;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, JwtUtils jwtUtils, UserDetailsServiceImple userDetailsServiceImple,
            Functions functions, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.userDetailsServiceImple = userDetailsServiceImple;
        this.functions = functions;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> authenticateUser(OAuth2AuthenticationToken authentication) {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        ResponseEntity<?> userValidate = userService.getUserByEmail(user.getAttribute("email"));
        if (userValidate.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            return userValidate;
        }

        User userDB = (User) userValidate.getBody();
        if (Objects.isNull(userDB)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found, must register first"));
        }

        String token = jwtUtils.generateToken(userDB);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new AuthenticationResponse(token, userDB.getUsername(), new Date(), userDB.getUserRoles()));

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @Override
    public ResponseEntity<?> login(AuthenticationRequest authenticationRequest) {
        ResponseEntity<?> userResponse = userService.getUserByEmail(authenticationRequest.getUsername());

        if (userResponse.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            return userResponse;
        }

        User user = (User) userResponse.getBody();

        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("USUARIO NO ENCONTRADO"));
        }

        try {
            autenticar(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("CREDENCIALES INVALIDAS"));
        }

        UserDetails userDetails = userDetailsServiceImple.loadUserByUsername(authenticationRequest.getUsername());
        if (Objects.isNull(userDetails)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("USUARIO NO ENCONTRADO"));
        }

        String token = jwtUtils.generateToken(userDetails);
        if (Objects.isNull(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("ERROR AL GENERAR EL TOKEN"));
        }

        Date fechaLastSession = null;
        try {
            fechaLastSession = functions.obtenerFechaYhora();
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR AL OBTENER LA FECHA Y HORA"));
        }

        AuthenticationResponse response = new AuthenticationResponse(
                token, userDetails.getUsername(), fechaLastSession, new ArrayList<>(user.getUserRoles()));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception("USUARIO DESHABILITADO " + disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Credenciales Invalidas " + badCredentialsException.getMessage());
        }

    }

}
