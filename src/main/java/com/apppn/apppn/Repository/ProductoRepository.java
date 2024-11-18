package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    Producto findByProducto(String producto);

}
