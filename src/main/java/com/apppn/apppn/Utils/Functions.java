package com.apppn.apppn.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.AplicarPagoDTO;
import com.apppn.apppn.Models.Cuotas;
import com.apppn.apppn.Models.PlanPagos;
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

    public String obtenerUsernameByToken() {
        try {
            String token = request.getAttribute("token").toString();
            String username = jwtUtils.extractUsername(token);
            return username;
        } catch (Exception e) {
            return null;
        }

    }

    public Double obtenerValorLiquidado(List<ProductoCompraFacturacion> productoCompraFacturacion) {
        Double valorLiquidado = 0.0;
        for (ProductoCompraFacturacion productoCompraFacturacionDB : productoCompraFacturacion) {
            Double valorVenta = productoCompraFacturacionDB.getValorVenta();
            Double valorTotalCosto = (productoCompraFacturacionDB.getProductoCompraInventory().getProductoCompra().getCosto()
                    * productoCompraFacturacionDB.getCantidad())
                    + productoCompraFacturacionDB.getProductoCompraInventory().getProductoCompra().getFlete();

            valorLiquidado = valorLiquidado + (((valorVenta*productoCompraFacturacionDB.getCantidad()) - valorTotalCosto)
                    * productoCompraFacturacionDB.getFacturacion().getUser().getPorcentajeLiquidacion()) / 100;
        }

        return valorLiquidado>0?valorLiquidado:0;
    }


    public void calculoPlanPagos(List<Cuotas> cuotas, AplicarPagoDTO aplicarPagoDTO){
        Double valorPago = aplicarPagoDTO.getValor();
        for(Cuotas cuota:cuotas){

            Double saldoCuota = cuota.getSaldo();
            

            if(valorPago == 0){
                break;
            }
           
            if(saldoCuota >= valorPago){
                saldoCuota = saldoCuota-valorPago;
                cuota.setSaldo(saldoCuota);
                valorPago = 0.0;
                continue;
            }

            if(saldoCuota < valorPago){
                valorPago = valorPago - saldoCuota;
                cuota.setSaldo(0.0);

            }
           
        }
    }


    public  List<Date> generarIntervalos(Date minFecha, Date maxFecha) {
        List<Date> intervalos = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(minFecha);

        while (cal.getTime().compareTo(maxFecha) <= 0) {
            intervalos.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 15);
        }

        intervalos.add(maxFecha); // Añadir la fecha máxima como límite final
        return intervalos;
    }

    public List<Date> intervalosFechasByFechaInicial(Date fechaInicial, Integer periodo) {
        List<Date> intervalos = new ArrayList<>();
         

         // Instancia Calendar
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(fechaInicial);
 
         
 
         // Sumar de a 15 días hasta 3 sumas
         for (int i = 1; i <= periodo; i++) {
             calendar.add(Calendar.DAY_OF_MONTH, periodo);
             Date newDate = calendar.getTime();
             intervalos.add(newDate);
         }
         return intervalos;

    }


    public  Date stringToDate(String fecha) throws ParseException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(fecha);

        SimpleDateFormat formatSalida = new SimpleDateFormat("yyyy-MM-dd");
        String fechafor = formatSalida.format(date);

        Date fechaOk = formatSalida.parse(fechafor);

        return fechaOk;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }

    }
}
