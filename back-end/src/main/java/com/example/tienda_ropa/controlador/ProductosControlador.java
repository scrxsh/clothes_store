package com.example.tienda_ropa.controlador;

import com.example.tienda_ropa.model.ProductosModel;
import com.example.tienda_ropa.services.ProductosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
@CrossOrigin("*")
public class ProductosControlador {

    @Autowired
    ProductosServices productosServices;

    @GetMapping("/todos")
    public List<ProductosModel> obtenerTodo(){
        return productosServices.obtenerTodosProductos();
    }

    @PostMapping("/crear")
    public ProductosModel crear(@RequestBody ProductosModel productos){
        return productosServices.crearProductos(productos);
    }

    @PutMapping("/actualizar")
    public ProductosModel actualizar(@RequestBody ProductosModel productos){
        return productosServices.actualizarProductos(productos);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        productosServices.borrarProductos(id);
    }

}
