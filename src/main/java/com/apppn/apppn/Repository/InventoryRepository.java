package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Inventory;
import com.apppn.apppn.Models.User;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {



    @Query(value = "SELECT * FROM inventory LEFT JOIN producto_compra_inventory ON producto_compra_inventory.inventory_id = inventory.id_inventory WHERE producto_compra_inventory.user_id = :idUser AND inventory.facturacion_id IS  NULL", nativeQuery = true)
    List<Inventory> listarInventarioSinFacturacionByUser(Long idUser);


    @Query(value = "SELECT * FROM inventory LEFT JOIN producto_compra_inventory ON producto_compra_inventory.inventory_id = inventory.id_inventory WHERE producto_compra_inventory.user_id = :idUser AND inventory.facturacion_id IS NOT NULL", nativeQuery = true)
    List<Inventory> listarInventarioConFacturacionByUser(Long idUser);


    @Query(value = "SELECT DISTINCT * FROM `inventory` JOIN producto_compra_inventory ON producto_compra_inventory.inventory_id = inventory.id_inventory JOIN producto_compra ON producto_compra_inventory.producto_compra_id = producto_compra.id_producto_compra JOIN compra ON producto_compra.compra_id = compra.id_compra WHERE compra.pago_id = :idPago;", nativeQuery = true)
    List<Inventory> obtenerInventarioByIdPago(@Param("idPago") Long idPago);

}
