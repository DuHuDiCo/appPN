package com.apppn.apppn.Controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.DTO.Request.ClasificacionDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.ClasificacionProducto;
import com.apppn.apppn.Service.ClasificacionProductoService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/clasificacion")
public class ClasificacionProductoController {


    private final ClasificacionProductoService clasificacionProductoService;

    public ClasificacionProductoController(ClasificacionProductoService clasificacionProductoService) {
        this.clasificacionProductoService = clasificacionProductoService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClasificacionProducto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/")
    public ResponseEntity<?> getClasificacionProducto() {
        return clasificacionProductoService.getClasificacionProducto();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClasificacionProducto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/")
    public ResponseEntity<?> saveClasificacionProducto(@RequestBody ClasificacionDTO clasificacionProductoDTO) {
        return clasificacionProductoService.saveClasificacionProducto(clasificacionProductoDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SuccessException.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/{idClasificacionProducto}")
    public ResponseEntity<?> deleteClasificacionProducto(@PathVariable("idClasificacionProducto") Long idClasificacionProducto) {
        return clasificacionProductoService.deleteClasificacionProducto(idClasificacionProducto);
    }

}
