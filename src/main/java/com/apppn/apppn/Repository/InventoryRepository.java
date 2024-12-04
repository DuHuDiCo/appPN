package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Inventory;
import com.apppn.apppn.Models.User;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {



    @Query(value = "SELECT * FROM inventory LEFT JOIN producto_compra_inventory ON producto_compra_inventory.inventory_id = inventory.id_inventory WHERE producto_compra_inventory.user_id = :idUser AND inventory.facturacion_id :isnull", nativeQuery = true)
    List<Inventory> listarInventarioByUser(Long idUser, String isnull);

}
