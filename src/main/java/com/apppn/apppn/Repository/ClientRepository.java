package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.User;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameOrLastname(String name, String lastName);

    Client findByEmail(String email);

    List<Client> findByUser(User user);

    @Query(value="SELECT client.* FROM `client` LEFT JOIN producto_compra_facturacion ON producto_compra_facturacion.client_id = client.id_client LEFT JOIN facturacion ON producto_compra_facturacion.facturacion_id = facturacion.id_facturacion WHERE facturacion.id_facturacion = :idFacturacion", nativeQuery = true)
    List<Client> obtenerClientesByFacturacion(@Param("idFacturacion") Long idFacturacion);

}
