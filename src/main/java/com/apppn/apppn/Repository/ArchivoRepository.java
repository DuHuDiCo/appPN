package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Archivos;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivos, Long> {

}
