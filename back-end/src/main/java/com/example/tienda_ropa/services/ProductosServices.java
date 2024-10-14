package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.ProductosModel;
import com.example.tienda_ropa.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductosServices {

    @Autowired
    ProductosRepository productosRepository;
    @Autowired
    private UploadService uploadService;

    String url = "http://localhost:8080/uploads/images/";

    public List<ProductosModel> obtenerTodosProductos() {
        List<ProductosModel> productos = productosRepository.findAll();
        productos = productos.stream().map(producto -> {producto.setImgPrenda(url+producto.getImgPrenda());
            return producto;
        }).collect(Collectors.toList());
        return productos;
    }

    public ProductosModel crearProducto(ProductosModel producto, MultipartFile file) throws IOException {
        comprobacionImagen(file);
        String imgPrenda = uploadService.guardarArchivo(file);
        producto.setImgPrenda(imgPrenda);
        return productosRepository.save(producto);
    }

    public ProductosModel actualizarProducto(ProductosModel producto, MultipartFile file) throws IOException{
        if (producto.getIdPrenda() != null){
            comprobacionImagen(file);
            String urlImg = uploadService.guardarArchivo(file);
            producto.setImgPrenda(urlImg);
            return productosRepository.save(producto);
        }
        return null;
    }

    public void borrarProductos(Long id){
        ProductosModel producto = productosRepository.findById(id).get();
        String imgPrenda = producto.getImgPrenda();
        uploadService.eliminarArchivo(imgPrenda);
        productosRepository.delete(producto);
    }

    public void comprobacionImagen (MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("Archivo vacio");
        }

        String tipoContenido = file.getContentType();
        if (!"image".equals(tipoContenido)){
            throw new IllegalArgumentException("El archivo debe ser imagen");
        }
    }
}
