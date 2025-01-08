package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.User;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {



    @Query(value = "SELECT DISTINCT facturacion.* FROM `facturacion` LEFT JOIN producto_compra_facturacion ON producto_compra_facturacion.facturacion_id = facturacion.id_facturacion WHERE producto_compra_facturacion.liquidacion_id is null AND facturacion.user_id = :idUser AND producto_compra_facturacion.liquidacion_id IS NULL", nativeQuery = true)
    List<Facturacion> obtejFacturacions(@Param("idUser") Long idUser);


    @Query(value = "SELECT DISTINCT facturacion.* FROM `facturacion` LEFT JOIN producto_compra_facturacion ON producto_compra_facturacion.facturacion_id = facturacion.id_facturacion WHERE producto_compra_facturacion.client_id = :idCliente", nativeQuery = true)
    List<Facturacion> obteneFacturacions(@Param("idCliente") Long idCliente);


    List<Facturacion> findByUser(User user);


    @Query(value = "SELECT facturacion.* FROM `client` LEFT JOIN producto_compra_facturacion ON producto_compra_facturacion.client_id = client.id_client LEFT JOIN facturacion ON producto_compra_facturacion.facturacion_id = facturacion.id_facturacion LEFT JOIN plan_pagos ON plan_pagos.facturacion_id = facturacion.plan_pagos_id WHERE plan_pagos.facturacion_id IS NULL AND client.id_client = :idCliente AND producto_compra_facturacion.tipo_venta_id = 2", nativeQuery = true)
    List<Facturacion> obtenerFacturacionByClient(@Param("idCliente") Long idCliente);


    @Query(value = "SELECT DISTINCT facturacion.* FROM `cuotas` LEFT JOIN facturacion ON cuotas.plan_pagos_id = facturacion.id_facturacion WHERE cuotas.fecha_pago <= now() AND cuotas.saldo > 0 LIMIT 1;", nativeQuery = true)
    Facturacion obtenerFacturacionByCuotaVencida();


    @Query(value = "SELECT DISTINCT facturacion.* FROM `cuotas` LEFT JOIN facturacion ON cuotas.plan_pagos_id = facturacion.id_facturacion WHERE cuotas.fecha_pago > now() AND cuotas.saldo > 0 LIMIT 1;", nativeQuery = true)
    Facturacion obtenerFacturacionByCuotaProx();
}
