package com.apppn.apppn.Controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Archivos;
import com.apppn.apppn.Service.ArchivoService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/files")
public class ArchivosController {

    private final ArchivoService archivosService;

    public ArchivosController(ArchivoService archivosService) {
        this.archivosService = archivosService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Archivos.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/")
    public CompletableFuture<ResponseEntity<Object>> getArchivos(@RequestParam("file") Long file) {
        return archivosService.getFiles(file);
    }

    

}
