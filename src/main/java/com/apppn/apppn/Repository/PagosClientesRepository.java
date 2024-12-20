package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.PagoClientes;

@Repository
public interface PagosClientesRepository extends JpaRepository<PagoClientes, Long> {


    

}
