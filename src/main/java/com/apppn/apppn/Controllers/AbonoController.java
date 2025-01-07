package com.apppn.apppn.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.DTO.Request.PagoClienteDTO;
import com.apppn.apppn.DTO.Response.AbonoDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Service.AbonoService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/abono")
public class AbonoController {


    private final AbonoService abonoService;

    public AbonoController(AbonoService abonoService) {
        this.abonoService = abonoService;
    }   

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SuccessException.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/crear")
    public ResponseEntity<?> crearAbono(@RequestBody AbonoDTO pagoClientesDto) {
        return abonoService.crearAbono(pagoClientesDto);
    }

}
