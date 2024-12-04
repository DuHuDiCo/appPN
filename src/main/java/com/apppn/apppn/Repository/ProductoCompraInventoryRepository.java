package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.ProductoCompraInventory;

@Repository
public interface ProductoCompraInventoryRepository extends JpaRepository<ProductoCompraInventory, Long> {

}
