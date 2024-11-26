package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Liquidacion;

@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion, Long> {

}
