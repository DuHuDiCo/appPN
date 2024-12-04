package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Compra;
import com.apppn.apppn.Models.Pago;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {


    Compra findByPago(Pago pago);
}
