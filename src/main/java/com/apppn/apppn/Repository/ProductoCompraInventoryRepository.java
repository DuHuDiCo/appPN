package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.ProductoCompraInventory;

@Repository
public interface ProductoCompraInventoryRepository extends JpaRepository<ProductoCompraInventory, Long> {


    @Query(value = "SELECT DISTINCT producto_compra_inventory.* FROM `inventory` LEFT JOIN producto_compra_inventory ON producto_compra_inventory.inventory_id = inventory.id_inventory WHERE inventory.quantity_sin_facturacion > 0 AND producto_compra_inventory.user_id = :idUser ", nativeQuery = true)
    List<ProductoCompraInventory> obtenerProductosInventarioSinFacturacion(@Param("idUser") Long idUser);



    
}
