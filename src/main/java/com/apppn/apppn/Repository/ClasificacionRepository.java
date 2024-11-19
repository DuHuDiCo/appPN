package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.ClasificacionProducto;

@Repository
public interface ClasificacionRepository extends JpaRepository<ClasificacionProducto, Long> {

    ClasificacionProducto findByClasificacionProducto(String clasificacionProducto);

}
