package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.PagoClientes;

@Repository
public interface PagosClientesRepository extends JpaRepository<PagoClientes, Long> {



    @Query(value = "SELECT * FROM pago_clientes LEFT JOIN facturacion ON pago_clientes.facturacion_id = facturacion.id_facturacion LEFT JOIN producto_compra_facturacion ON producto_compra_facturacion.facturacion_id = facturacion.id_facturacion WHERE producto_compra_facturacion.client_id = :idCliente", nativeQuery = true)
    List<PagoClientes> findByClient(@Param("idCliente") Long idCliente);

    
    List<PagoClientes> findByIsAplicado(Boolean isAplicado);

}
