package com.apppn.apppn.Controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apppn.apppn.DTO.Request.ProductDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.Producto;
import com.apppn.apppn.Service.ProductService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/product")
public class ProductoController {

    private final ProductService productoService;

    public ProductoController(ProductService productoService) {
        this.productoService = productoService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PostMapping(value = "/", consumes = "*/*")
    public ResponseEntity<?> saveProduct(
            @RequestPart("producto") String producto,
            @RequestPart("descripcion") String descripcion,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            @RequestPart("clasificacionProducto") Long clasificacionProducto) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProducto(producto);
        productDTO.setDescripcion(descripcion);
        if(Objects.nonNull(productDTO.getImagen())){
            productDTO.setImagen(imagen);
        }
        productDTO.setClasificacionProducto(clasificacionProducto);

        return productoService.createProduct(productDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SuccessException.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> deleteProduct(@PathVariable("idProducto") Long idProducto) {
        return productoService.deleteProduct(idProducto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PutMapping("/{idProducto}")
    public ResponseEntity<?> editProduct(@PathVariable("idProducto") Long idProducto,
            @ModelAttribute ProductDTO producto) {
        return productoService.editProduct(idProducto, producto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping("/")
    public ResponseEntity<?> getProducts() {
        return productoService.getProducts();
    }

}
