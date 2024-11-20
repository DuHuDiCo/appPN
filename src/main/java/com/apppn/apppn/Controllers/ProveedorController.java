package com.apppn.apppn.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.DTO.Request.ProveedorDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.Proveedor;
import com.apppn.apppn.Service.ProveedorService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {


    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            
    })
    @PostMapping("/")
    public ResponseEntity<?> createProveedor(@RequestBody ProveedorDTO proveedor) {
        return proveedorService.createProveedor(proveedor);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            
    })
    @GetMapping("/")
    public ResponseEntity<?> getProveedores() {
        return proveedorService.getProveedores();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            
    })
    @PutMapping("/{idProveedor}")
    public ResponseEntity<?> editProveedor(@PathVariable("idProveedor") Long idProveedor, @RequestBody ProveedorDTO proveedor) {
        return proveedorService.editProveedor(idProveedor, proveedor);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SuccessException.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            
    })
    @DeleteMapping("/{idProveedor}")
    public ResponseEntity<?> deleteProveedor(@PathVariable("idProveedor") Long idProveedor) {
        return proveedorService.deleteProveedor(idProveedor);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            
    })
    @GetMapping("/")
    public ResponseEntity<?> getProveedor(@RequestParam("dato") String dato) {
        return proveedorService.getProveedor(dato);
    }

}
