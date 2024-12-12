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

}
