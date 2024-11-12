package com.example.tienda_ropa.controllers;
import com.example.tienda_ropa.models.ProductoModel;
import com.example.tienda_ropa.models.ProveedorModel;
import com.example.tienda_ropa.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/proveedores")
@CrossOrigin("*")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;
    @GetMapping("/todos")
    public List<ProveedorModel> obtenerTodo(){
        return proveedorService.obtenerTodosProveedores();
    }

    @GetMapping("{id_proveedor}/productos")
    public ResponseEntity<List<ProductoModel>> obtenerProductosPorProveedor(@PathVariable Long id_proveedor){
        List<ProductoModel> productos = proveedorService.obtenerProductosPorProveedor(id_proveedor);
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/crear")
    public ProveedorModel crear(@RequestBody ProveedorModel proveedores){
        return proveedorService.crearProveedores(proveedores);
    }
    @PutMapping("/actualizar")
    public ProveedorModel actualizar(@RequestBody ProveedorModel proveedores){
        return proveedorService.actualizarProveedores(proveedores);
    }
    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        proveedorService.borrarProveedores(id);
    }
}
