package com.apppn.apppn.Controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.TipoVenta;
import com.apppn.apppn.Service.TipoVentaService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/tipoVenta")
public class TipoVentaController {

    private final TipoVentaService tipoVentaService;

    public TipoVentaController(TipoVentaService tipoVentaService) {
        this.tipoVentaService = tipoVentaService;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TipoVenta.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @GetMapping("/tipoVentas")
    public ResponseEntity<?> obtenerTipoVentas() {
        return tipoVentaService.obtenerTipoVentas();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TipoVenta.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @PostMapping("/")
    public ResponseEntity<?> crearTipoVenta(@RequestBody Map<String, String> tipoVenta) {
        return tipoVentaService.crearTipoVenta(tipoVenta);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TipoVenta.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @DeleteMapping("/{idTipoVenta}")
    public ResponseEntity<?> eliminarTipoVenta(@PathVariable("idTipoVenta") Long idTipoVenta) {
        return tipoVentaService.eliminarTipoVenta(idTipoVenta);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TipoVenta.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @GetMapping("/byName")
    public ResponseEntity<?> getTipoVenta(@RequestParam("tipoVenta") String nombre) {
        return tipoVentaService.obtenerTipoVenta(nombre);
    }
}
