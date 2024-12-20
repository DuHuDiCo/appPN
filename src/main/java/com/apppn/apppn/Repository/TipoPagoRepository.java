package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.TipoPago;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {

    TipoPago findByNombreTipoPago(String nombreTipoPago);

}
