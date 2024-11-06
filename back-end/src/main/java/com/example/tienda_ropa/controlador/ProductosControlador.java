package com.example.tienda_ropa.controlador;

import com.example.tienda_ropa.model.ProductosModel;
import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.services.ProductosServices;
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
public class ProductosControlador {

    @Autowired
    ProductosServices productosServices;

    @GetMapping("/todos")
    public ResponseEntity<List<ProductosModel>> obtenerTodo(){
        return new ResponseEntity<>(productosServices.obtenerTodosProductos(), HttpStatus.OK);
    }

    @GetMapping("{id_producto}/ventas")
    public ResponseEntity<List<VentasModel>> obtenerListaVentasProducto(@PathVariable Long id_producto){
        List<VentasModel> ventas = productosServices.obtenerProductoVendido(id_producto);
        return ResponseEntity.ok(ventas);
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductosModel> crear(@RequestPart MultipartFile file, @RequestPart ProductosModel producto, @RequestParam Long id_proveedor){
        try {
            return new ResponseEntity<>(productosServices.crearProducto(producto, file,id_proveedor), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProductosModel> actualizar(@RequestPart MultipartFile file, @RequestPart ProductosModel producto, @RequestParam Long id_proveedor){
        try {
            ProductosModel productoActualizado = productosServices.actualizarProducto(producto, file, id_proveedor);
            return  productoActualizado != null ? new ResponseEntity<>(productoActualizado, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        productosServices.borrarProductos(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
