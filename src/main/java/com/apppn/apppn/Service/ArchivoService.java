package com.apppn.apppn.Service;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.apppn.apppn.Models.Archivos;

public interface ArchivoService {


    public ResponseEntity<?> saveFile(MultipartFile file, String path);


    public ResponseEntity<?> deleteFile(Archivos archivos);


    public CompletableFuture<ResponseEntity<?>>  getFiles(Long idFile);

}
