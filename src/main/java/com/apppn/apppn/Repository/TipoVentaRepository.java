package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.TipoVenta;

@Repository
public interface TipoVentaRepository extends JpaRepository<TipoVenta, Long> {

    TipoVenta findByTipoVenta(String tipoVenta);

    List<TipoVenta> findByNombreTipoVenta(String tipoVenta);

}
