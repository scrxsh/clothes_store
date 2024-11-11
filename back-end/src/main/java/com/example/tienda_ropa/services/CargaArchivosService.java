package com.example.tienda_ropa.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class CargaArchivosService {


    private final String url = "uploads/images/";


    public String guardarArchivo(MultipartFile file, String nombre) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String nombreOriginal = Objects.requireNonNull(file.getOriginalFilename());
            String extension = nombreOriginal.substring(file.getOriginalFilename().lastIndexOf("."));
            String encode = URLEncoder.encode(nombre, StandardCharsets.UTF_8) + extension;
            Path path = Paths.get(url + encode);
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);
            return encode;
        }
        return null;
    }


    public void eliminarArchivo(String imgPrenda){
        File file = new File(url+imgPrenda);
        file.delete();
    }
}
