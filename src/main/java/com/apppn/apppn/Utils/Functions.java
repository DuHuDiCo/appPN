package com.apppn.apppn.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.apppn.apppn.Models.ProductoCompraFacturacion;
import com.apppn.apppn.Security.Security.JwtUtils;

@Service
public class Functions {


    private final HttpServletRequest request;
    private final JwtUtils jwtUtils;

    public Functions(HttpServletRequest request, JwtUtils jwtUtils) {
        this.request = request;
        this.jwtUtils = jwtUtils;
    }

    public boolean isTokenExpired(Instant tokenExpiryTime) {
        return Instant.now().isAfter(tokenExpiryTime);
    }


    public Date obtenerFechaYhora() throws ParseException {
        // Obtener la fecha y hora actual en milisegundos
        long tiempoActual = System.currentTimeMillis();

        // Crear un objeto de fecha y hora usando el tiempo actual
        Date fecha = new Date(tiempoActual);

        // Configurar el formato de la fecha y la zona horaria a GMT-5 (Bogotá)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Formatear la fecha y la hora con la zona horaria de Bogotá
        String fechaTexto = formatter.format(fecha);

        // Convertir el texto formateado nuevamente a un objeto de fecha y hora
        Date fechaFormateada = formatter.parse(fechaTexto);

        // Devolver la fecha y hora formateada
        return fechaFormateada;

    }


    public String obtenerUsernameByToken(){
        try {
            String token = request.getAttribute("token").toString();
        String username = jwtUtils.extractUsername(token);
        return username;
        } catch (Exception e) {
            return null;
        }
        
    }


    public Double obtenerValorLiquidado(List<ProductoCompraFacturacion> productoCompraFacturacion) {
        for (ProductoCompraFacturacion productoCompraFacturacionDB : productoCompraFacturacion) {

        }
        return null;
    }

}
