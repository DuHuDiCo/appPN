package com.apppn.apppn.ServiceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apppn.apppn.DTO.Request.ProveedorDTO;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Exceptions.SuccessException;
import com.apppn.apppn.Models.Proveedor;
import com.apppn.apppn.Repository.ProveedorRepository;
import com.apppn.apppn.Service.ProveedorService;
import com.apppn.apppn.Utils.Functions;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;

@Service
public class ProovedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final Functions functions;

    public ProovedorServiceImpl(ProveedorRepository proveedorRepository, Functions functions) {
        this.proveedorRepository = proveedorRepository;
        this.functions = functions;
    }

    @Override
    public ResponseEntity<?> createProveedor(ProveedorDTO proveedorDto) {
        Proveedor proveedor = proveedorRepository.findByProveedor(proveedorDto.getProveedor());
        if(Objects.nonNull(proveedor)){
            return ResponseEntity.badRequest().body(new ErrorResponse("El proveedor ya existe"));
        }

        proveedor = new Proveedor();
        proveedor.setProveedor(proveedorDto.getProveedor());
        proveedor.setDireccion(proveedorDto.getDireccion());
        proveedor.setTelefono(proveedorDto.getTelefono());
        proveedor.setCiudad(proveedorDto.getCiudad());
        proveedor.setBanco(proveedorDto.getBanco());
        proveedor.setEmail(proveedorDto.getEmail());

        proveedor = proveedorRepository.save(proveedor);
        return ResponseEntity.status(HttpStatus.OK).body(proveedor);
        

    }

    @Override
    public ResponseEntity<?> getProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(proveedores);
    }

    @Override
    public ResponseEntity<?> editProveedor(Long idProveedor, ProveedorDTO proveedor) {
       Proveedor proveedorEdit = proveedorRepository.findById(idProveedor).orElse(null);
       if(Objects.isNull(proveedorEdit)){
           return ResponseEntity.badRequest().body(new ErrorResponse("El proveedor no existe"));
       }
       proveedorEdit.setProveedor(proveedor.getProveedor());
       proveedorEdit.setDireccion(proveedor.getDireccion());
       proveedorEdit.setTelefono(proveedor.getTelefono());
       proveedorEdit.setCiudad(proveedor.getCiudad());
       proveedorEdit.setBanco(proveedor.getBanco());
       proveedorEdit.setEmail(proveedor.getEmail());
       
       proveedorRepository.save(proveedorEdit);
       return ResponseEntity.status(HttpStatus.OK).body(proveedorEdit);
    }

    @Override
    public ResponseEntity<?> deleteProveedor(Long idProveedor) {
        Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
        if(Objects.isNull(proveedor)){
            return ResponseEntity.badRequest().body(new ErrorResponse("El proveedor no existe"));
        }
        proveedorRepository.delete(proveedor);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessException("El proveedor ha sido eliminado"));

    }

    @Override
    public ResponseEntity<?> getProveedor(String dato) {
        List<Proveedor> proveedores = proveedorRepository.findByProveedorContainingIgnoreCase(dato);
        if(CollectionUtils.isEmpty(proveedores)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("El proveedor no existe"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(proveedores);

    }

}
