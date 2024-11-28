package com.apppn.apppn.ServiceImpl;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.Archivos;
import com.apppn.apppn.Repository.ArchivoRepository;
import com.apppn.apppn.Service.ArchivoService;
import com.apppn.apppn.Utils.SaveFiles;

@Service
public class ArchivoServiceImpl implements ArchivoService {

    @Value("${url-back}")
    private String urlBack;

    private final SaveFiles saveFiles;
    private final ArchivoRepository archivoRepository;

    public ArchivoServiceImpl(SaveFiles saveFiles, ArchivoRepository archivoRepository) {
        this.saveFiles = saveFiles;
        this.archivoRepository = archivoRepository;
    }

    @Override
    public ResponseEntity<?> saveFile(MultipartFile file, String path) {
        Archivos archivo;
        try {
            archivo = saveFiles.guardarFileMultiPartFile(file, path);
            if (Objects.isNull(file)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Error al subir imagen"));
            }
            archivo = archivoRepository.save(archivo);

            archivo.setUrlPath(urlBack.concat("/api/v1/files/?file=").concat(String.valueOf(archivo.getIdArchivo())));
            

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
        

        archivo = archivoRepository.save(archivo);
        
        return ResponseEntity.status(HttpStatus.OK).body(archivo);  
    }

    @Override
    public ResponseEntity<?> deleteFile(Archivos archivos) {

        archivos = archivoRepository.findById(archivos.getIdArchivo()).orElse(null);
        if (Objects.isNull(archivos)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Archivo no encontrado"));
        }

        archivoRepository.delete(archivos);
        return ResponseEntity.status(HttpStatus.OK).body(archivos);
    }

    @Override
    public CompletableFuture<ResponseEntity<?>> getFiles(Long idFile) {
        return CompletableFuture.supplyAsync(() -> {
            Archivos archivos = archivoRepository.findById(idFile).orElse(null);
            if (Objects.isNull(archivos)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Archivo no encontrado"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(archivos);
        });
    }

   
}
