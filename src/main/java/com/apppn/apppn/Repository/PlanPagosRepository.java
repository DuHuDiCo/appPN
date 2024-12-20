package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.PlanPagos;

@Repository
public interface PlanPagosRepository extends JpaRepository<PlanPagos, Long> {

    

}
