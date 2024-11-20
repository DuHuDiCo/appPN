package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
