package com.apppn.apppn.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apppn.apppn.DTO.Request.ClientDTO;
import com.apppn.apppn.DTO.Request.PlanPagosDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.Cuotas;
import com.apppn.apppn.Models.Facturacion;
import com.apppn.apppn.Models.PlanPagos;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.ClientRepository;
import com.apppn.apppn.Repository.FacturacionRepository;
import com.apppn.apppn.Security.Security.JwtUtils;
import com.apppn.apppn.Service.ClientService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final HttpServletRequest request;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final FacturacionRepository facturacionRepository;
    private final Functions functions;

    
    

    public ClientServiceImpl(ClientRepository clientRepository, HttpServletRequest request, JwtUtils jwtUtils,
            UserService userService, FacturacionRepository facturacionRepository, Functions functions) {
        this.clientRepository = clientRepository;
        this.request = request;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.facturacionRepository = facturacionRepository;
        this.functions = functions;
    }

    @Override
    public ResponseEntity<?> saveClient(ClientDTO clientDTO) {
        Client newClient = clientRepository.findByEmail(clientDTO.getEmail());
        if (Objects.nonNull(newClient)) {
            return ResponseEntity.status(400).body("Client already exists");
        }

        String token = request.getAttribute("token").toString();
        if (Objects.isNull(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no encontrado");
        }

        String username = jwtUtils.extractUsername(token);
        if (Objects.isNull(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ERROR AL EXTRAER EL NOMBRE DE USUARIO");
        }

        ResponseEntity<?> userResponse = userService.getUserByEmail(username);
        if (!userResponse.getStatusCode().equals(HttpStatus.OK)) {
            return userResponse;
        }
        User user = (User) userResponse.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }


        newClient = new Client();
        newClient.setName(clientDTO.getName());
        newClient.setLastname(clientDTO.getLastname());
        newClient.setEmail(clientDTO.getEmail());
        newClient.setPhone(clientDTO.getPhone());
        newClient.setEnabled(clientDTO.getEnabled());
        newClient.setUser(user);

        newClient = clientRepository.save(newClient);
        
        return ResponseEntity.status(HttpStatus.OK).body(newClient);
    }

    @Override
    public ResponseEntity<?> getClients() {
        String token = request.getAttribute("token").toString();
        if (Objects.isNull(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no encontrado");
        }

        String username = jwtUtils.extractUsername(token);
        if (Objects.isNull(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ERROR AL EXTRAER EL NOMBRE DE USUARIO");
        }

        ResponseEntity<?> userResponse = userService.getUserByEmail(username);
        if (!userResponse.getStatusCode().equals(HttpStatus.OK)) {
            return userResponse;
        }
        User user = (User) userResponse.getBody();
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        List<Client> clients = clientRepository.findByUser(user);
        if (CollectionUtils.isEmpty(clients)) {
            clients = new ArrayList<>();
        }
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @Override
    public ResponseEntity<?> editClient(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id).orElse(null);
        if (Objects.isNull(client)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El cliente no existe"));
        }
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setEnabled(clientDTO.getEnabled());

        client = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @Override
    public ResponseEntity<?> deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (Objects.isNull(client)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El cliente no existe"));
        }
        clientRepository.delete(client);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessException("El cliente ha sido eliminado"));
    }

    @Override
    public ResponseEntity<?> getClient(String dato) {
        String[] datos = dato.split(" ");
        List<Client> client = clientRepository.findByNameOrLastname(datos[0], datos.length > 1 ? datos[1] : null);
        if (CollectionUtils.isEmpty(client)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @Override
    public ResponseEntity<?> crearPlanPago(Long idCliente, Long idFacturacion, PlanPagosDTO planPagosDTO) {
        Client client = clientRepository.findById(idCliente).orElse(null);
        if (Objects.isNull(client)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Cliente no encontrado"));
        }

        Facturacion facturacion = facturacionRepository.findById(idFacturacion).orElse(null);
        if (Objects.isNull(facturacion)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Facturacion no encontrada"));
        }

        PlanPagos planPagos = new PlanPagos();
        planPagos.setFacturacion(facturacion);
        planPagos.setClient(client);
        planPagos.setValor(client.getProductoCompraFacturacion().stream().map(pp -> pp.getValorVenta()).reduce(0.0, Double::sum));

        for (int index = 0; index < planPagosDTO.getCuotas(); index++) {
            Cuotas cuotas = new Cuotas();
            cuotas.setValorCuota(planPagosDTO.getValorCuota());
            cuotas.setSaldo(planPagosDTO.getValorCuota());
            cuotas.setFechaPago(planPagosDTO.getFechaCorte());

            List<Date> intervalos = functions.intervalosFechasByFechaInicial(planPagosDTO.getFechaCorte(), planPagosDTO.getPerodicidad());

            cuotas.setFechaPago(intervalos.get(index));
            planPagos.agrgarCuotas(cuotas);
            
        }

        client.agregarPlanPagos(planPagos);
        client = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(planPagos);

        
    }

}
