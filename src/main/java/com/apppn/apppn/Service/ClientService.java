package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.ClientDTO;
import com.apppn.apppn.DTO.Request.PlanPagosDTO;

public interface ClientService {


    public ResponseEntity<?> saveClient(ClientDTO clientDTO);

    public ResponseEntity<?> getClients();

    public ResponseEntity<?> editClient(Long id, ClientDTO clientDTO);

    public ResponseEntity<?> deleteClient(Long id);

    public ResponseEntity<?> getClient(String dato);


    public ResponseEntity<?> crearPlanPago(Long idCliente, Long idFacturacion,PlanPagosDTO planPagosDTO);
    

    public ResponseEntity<?> getFacturacionesByClientSinPlanPago(Long idCliente);
}
