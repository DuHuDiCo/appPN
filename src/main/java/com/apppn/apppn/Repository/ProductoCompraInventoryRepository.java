package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.ProductoCompraInventory;

@Repository
public interface ProductoCompraInventoryRepository extends JpaRepository<ProductoCompraInventory, Long> {


    @Query(value = "SELECT producto_compra_inventory.* FROM `producto_compra_inventory` LEFT JOIN producto_compra_facturacion ON producto_compra_facturacion.producto_compra_inventory_id = producto_compra_inventory.id_producto_compra_inventory WHERE producto_compra_facturacion.producto_compra_inventory_id is null AND producto_compra_inventory.user_id = :idUser", nativeQuery = true)
    List<ProductoCompraInventory> obtenerProductosInventarioSinFacturacion(@Param("idUser") Long idUser);


}
