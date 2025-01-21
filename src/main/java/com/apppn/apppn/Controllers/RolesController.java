package com.apppn.apppn.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Service.RoleService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v1/role")
public class RolesController {


    private final RoleService roleService;

    



    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }





    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/")
    public ResponseEntity<?> getPermissions() {
        return roleService.getPermissions();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPermissionById(@PathVariable("id") Long id) {
        return roleService.getPermissionById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/role/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable("id") Long id) {
        return roleService.getRoleById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/role")
    public ResponseEntity<?> getRoles() {
        return roleService.getRoles();
    }

}
