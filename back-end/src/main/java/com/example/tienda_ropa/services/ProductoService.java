package com.example.tienda_ropa.services;

import com.example.tienda_ropa.models.ProductoModel;
import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.repositories.ProductoRepository;
import com.example.tienda_ropa.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    CargaArchivosService cargaArchivosService;


    String url = "http://localhost:8080/uploads/images/";

    public List<ProductoModel> obtenerTodosProductos() {
        List<ProductoModel> productos = productoRepository.findAll();
        productos = productos.stream().peek(producto -> producto.setImgPrenda(url+producto.getImgPrenda())).collect(Collectors.toList());
        return productos;
    }

    public List<VentaModel> obtenerProductoVendido(Long id_producto){
        ProductoModel producto = productoRepository.findById(id_producto).orElse(null);
        return Objects.requireNonNull(producto).getVentasList();
    }

    public ProductoModel crearProducto(ProductoModel producto, MultipartFile file, Long id_proveedor) throws IOException {
        producto.setProveedor(proveedorRepository.findById(id_proveedor).orElse(null));
        comprobacionImagen(file);
        String urlImg = cargaArchivosService.guardarArchivo(file, producto.getNombre());
        producto.setImgPrenda(urlImg);
        return productoRepository.save(producto);
    }


    public ProductoModel actualizarProducto(ProductoModel producto, MultipartFile file, Long id_proveedor) throws IOException{
        if (producto.getId() != null){
            producto.setProveedor(proveedorRepository.findById(id_proveedor).orElse(null));
            comprobacionImagen(file);
            String urlImg = cargaArchivosService.guardarArchivo(file, producto.getNombre());
            producto.setImgPrenda(urlImg);
            return productoRepository.save(producto);
        }
        return null;
    }

    public void borrarProductos(Long id){
        ProductoModel producto = productoRepository.findById(id).get();
        String imgPrenda = producto.getImgPrenda();
        cargaArchivosService.eliminarArchivo(imgPrenda);
        productoRepository.delete(producto);
    }

    public void comprobacionImagen (MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("Archivo vacio");
        }
    }
}
