package com.apppn.apppn.ServiceImpl;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.ClientDTO;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Repository.ClientRepository;
import com.apppn.apppn.Service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;



    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<?> saveClient(ClientDTO clientDTO) {
        Client newClient = clientRepository.findByEmail(clientDTO.getEmail());
        if (Objects.nonNull(newClient)) {
            return ResponseEntity.status(400).body("Client already exists");
        }
        newClient = new Client();
        newClient.setName(clientDTO.getName());
        newClient.setLastname(clientDTO.getLastname());
        newClient.setEmail(clientDTO.getEmail());
        newClient.setPhone(clientDTO.getPhone());

        return null;
    }

    @Override
    public ResponseEntity<?> getClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClients'");
    }

    @Override
    public ResponseEntity<?> editClient(Long id, ClientDTO clientDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editClient'");
    }

    @Override
    public ResponseEntity<?> deleteClient(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClient'");
    }

}
