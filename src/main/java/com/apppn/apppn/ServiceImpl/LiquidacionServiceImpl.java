package com.apppn.apppn.ServiceImpl;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.LiquidacionDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Liquidacion;
import com.apppn.apppn.Models.ProductoCompraFacturacion;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.LiquidacionRepository;
import com.apppn.apppn.Repository.ProductoCompraFacturacionRepository;

import com.apppn.apppn.Service.LiquidacionService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;

@Service
public class LiquidacionServiceImpl implements LiquidacionService {

    private final LiquidacionRepository liquidacionRepository;
    private final UserService userService;
    private final Functions functions;
    private final ProductoCompraFacturacionRepository productocompraRepository;

    public LiquidacionServiceImpl(LiquidacionRepository liquidacionRepository, UserService userService,
            Functions functions, ProductoCompraFacturacionRepository productocompraRepository) {
        this.liquidacionRepository = liquidacionRepository;
        this.userService = userService;
        this.functions = functions;
        this.productocompraRepository = productocompraRepository;
    }

    @Override
    public ResponseEntity<?> saveLiquidacion(LiquidacionDTO liquidacionDTO) {
        Liquidacion liquidacion = new Liquidacion();

        List<ProductoCompraFacturacion> productoCompraFacturacion = productocompraRepository
                .findAllById(liquidacionDTO.getIdProductos());
        if (CollectionUtils.isEmpty(productoCompraFacturacion)) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("No existe el producto con ese id"));
        }
        try {
            liquidacion.setFecha(functions.obtenerFechaYhora());
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR AL PARSEAR LA FECHA Y HORA"));
        }


        Double valorLiquidado = functions.obtenerValorLiquidado(productoCompraFacturacion);

        for (ProductoCompraFacturacion productoCompraFacturacionDB : productoCompraFacturacion) {
            liquidacion.setValorVenta(liquidacion.getValorVenta() + productoCompraFacturacionDB.getValorVenta());
            liquidacion.agregarProductoCompraFacturacion(productoCompraFacturacionDB);
        }
        liquidacion.setValorLiquidado(valorLiquidado);

        ResponseEntity<?> response = userService.getUserById(productoCompraFacturacion.get(0).getFacturacion().getUser().getIdUser());
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return response;
        }
        User user = (User) response.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("USUARIO NO ENCONTRADO"));
        }


        liquidacion.setVendedor(user);
        liquidacion = liquidacionRepository.save(liquidacion);
        return ResponseEntity.status(HttpStatus.OK).body(liquidacion);
    }

    @Override
    public ResponseEntity<?> getLiquidacionesByUser(Long idUser) {

        ResponseEntity<?> response = userService.getUserById(idUser);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return response;
        }
        User user = (User) response.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("USUARIO NO ENCONTRADO"));
        }


        List<Liquidacion> liquidaciones = liquidacionRepository.findByVendedor(user);
        return ResponseEntity.status(HttpStatus.OK).body(liquidaciones);
        
    }

}
