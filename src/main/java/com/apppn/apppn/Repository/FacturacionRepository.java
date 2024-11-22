package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.User;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {

    List<Facturacion> findByUser(User user);

}
