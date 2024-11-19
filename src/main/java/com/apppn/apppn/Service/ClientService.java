package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.ClientDTO;

public interface ClientService {


    public ResponseEntity<?> saveClient(ClientDTO clientDTO);

    public ResponseEntity<?> getClients();

    public ResponseEntity<?> editClient(Long id, ClientDTO clientDTO);

    public ResponseEntity<?> deleteClient(Long id);

    

}
