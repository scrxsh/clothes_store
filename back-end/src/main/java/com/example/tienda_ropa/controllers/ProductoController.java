package com.example.tienda_ropa.controllers;

import com.example.tienda_ropa.models.ProductoModel;
import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/productos")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/todos")
    public ResponseEntity<List<ProductoModel>> obtenerTodo(){
        return new ResponseEntity<>(productoService.obtenerTodosProductos(), HttpStatus.OK);
    }

    @GetMapping("{id_producto}/ventas")
    public ResponseEntity<List<VentaModel>> obtenerListaVentasProducto(@PathVariable Long id_producto){
        List<VentaModel> ventas = productoService.obtenerProductoVendido(id_producto);
        return ResponseEntity.ok(ventas);
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoModel> crear(@RequestPart MultipartFile file, @RequestPart ProductoModel producto, @RequestParam Long id_proveedor){
        try {
            return new ResponseEntity<>(productoService.crearProducto(producto, file,id_proveedor), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProductoModel> actualizar(@RequestPart MultipartFile file, @RequestPart ProductoModel producto, @RequestParam Long id_proveedor){
        try {
            ProductoModel productoActualizado = productoService.actualizarProducto(producto, file, id_proveedor);
            return  productoActualizado != null ? new ResponseEntity<>(productoActualizado, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        productoService.borrarProductos(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
