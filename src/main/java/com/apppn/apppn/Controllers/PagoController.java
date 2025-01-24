package com.apppn.apppn.Controllers;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.DTO.Request.PagoDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Compra;
import com.apppn.apppn.Service.PagoService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/pago")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Compra.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @PostMapping("/{idCompra}")
    public ResponseEntity<?> crearPago(@PathVariable("idCompra") Long idCompra, @ModelAttribute PagoDTO pago) {
        return pagoService.crearPago(idCompra, pago);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @GetMapping()
    public ResponseEntity<?> obtenerPagos() {
        return pagoService.obtenerPagos();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @DeleteMapping("/{idPago}")
    public ResponseEntity<?> eliminarPagao(@PathVariable("idPago") Long idPago, @RequestParam("idInventario") Long idInventario) {
        return pagoService.eliminarPago(idPago, idInventario);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @GetMapping("/byFecha")
    public ResponseEntity<?> obtenerPago(@RequestParam("fecha") Date fecha) {
        return pagoService.obtenerPago(fecha);
    }

}
