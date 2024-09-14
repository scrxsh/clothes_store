package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.ProductosModel;
import com.example.tienda_ropa.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServices {

    @Autowired
    ProductosRepository productosRepository;

    public List<ProductosModel> obtenerTodosProductos() {
        return productosRepository.findAll();
    }

    public ProductosModel crearProductos(ProductosModel productos){
        return productosRepository.save(productos);
    }

    public ProductosModel actualizarProductos(ProductosModel productos){
        if (productos.getIdProductos() != null){
            return productosRepository.save(productos);
        }return null;
    }

    public void borrarProductos(Long id){
        productosRepository.deleteById(id);
    }

}
