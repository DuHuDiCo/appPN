package com.apppn.apppn.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByfechaPagoBetween(Date fechaPagoInicio, Date fechaPagoFinal);
}
