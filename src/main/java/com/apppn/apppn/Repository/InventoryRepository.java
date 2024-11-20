package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Inventory;
import com.apppn.apppn.Models.User;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {


    List<Inventory> findByUser(User user);

}
