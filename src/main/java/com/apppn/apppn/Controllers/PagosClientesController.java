package com.apppn.apppn.Controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apppn.apppn.DTO.Request.AplicarPagoDTO;
import com.apppn.apppn.DTO.Request.PagoClienteDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.PagoClientes;
import com.apppn.apppn.Service.PagosClientesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/pagosClientes")
public class PagosClientesController {

    private final PagosClientesService pagosClientesService;

    public PagosClientesController(PagosClientesService pagosClientesService) {
        this.pagosClientesService = pagosClientesService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PagoClientes.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/")
    public ResponseEntity<?> crearPagoClientes(@RequestPart("pagoClientesDto") String pagoClientesDtoJson,
            @RequestPart("pagoClientesDto.file") MultipartFile file) {
        ObjectMapper mapper = new ObjectMapper();
        PagoClienteDTO request = null;
        try {
            request = mapper.readValue(pagoClientesDtoJson,
                    PagoClienteDTO.class);

            // Asignar el archivo al objeto request
            request.setComprobante(file);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
        if (Objects.isNull(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR DE PARSEO EN DOCUMENTACION GENERAL REQUEST");
        }
        return pagosClientesService.crearPagoClientes(request);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PagoClientes.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{idPagoCliente}")
    public ResponseEntity<?> consultarPagoClientes(@PathVariable Long idPagoCliente) {
        return pagosClientesService.consultarPagoClientes(idPagoCliente);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PagoClientes.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/{idPagoCliente}")
    public ResponseEntity<?> eliminarPagoClientes(@PathVariable Long idPagoCliente) {
        return pagosClientesService.eliminarPagoClientes(idPagoCliente);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PagoClientes.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/")
    public ResponseEntity<?> listarPagosClientes() {
        return pagosClientesService.listarPagosClientes();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PagoClientes.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/sinAplicar")
    public ResponseEntity<?> pagosSinAplicar() {
        return pagosClientesService.pagosSinAplicar();
    }

}
