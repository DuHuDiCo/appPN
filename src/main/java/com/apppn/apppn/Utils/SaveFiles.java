package com.apppn.apppn.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apppn.apppn.Models.Archivos;

@Service
public class SaveFiles {


    private final Functions functions;


    

    public SaveFiles(Functions functions) {
        this.functions = functions;
    }


    public Archivos guardarFileMultiPartFile(MultipartFile file, String ruta) throws IOException {

        // MultipartFile multipartFile = file;
        try {
            String name = saveFile(file.getBytes(), file.getOriginalFilename(),
                    ruta);
            Archivos archivo = new Archivos();
            archivo.setRuta(ruta.concat(name));
            archivo.setExtention(file.getContentType());
            try {
                archivo.setFechaCreacion(functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
            archivo.setFilename(name);
            archivo.setSize(file.getSize());

            return archivo;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public String saveFile(byte[] bytes, String name, String ruta) throws FileNotFoundException, IOException {

        Path path = Paths.get(ruta.concat(name));
        Files.write(path, bytes);
        return name;

    }

}
