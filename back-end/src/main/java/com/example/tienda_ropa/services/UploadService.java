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
public class UploadService {
    private final String url = "back-end/uploads/images/";

    public String guardarArchivo(MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            String encode = URLEncoder.encode(Objects.requireNonNull(file.getOriginalFilename()), StandardCharsets.UTF_8);
            Path path = Paths.get(url+encode);
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
