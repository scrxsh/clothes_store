package com.example.tienda_ropa.controlador;
import com.example.tienda_ropa.model.ProductosModel;
import com.example.tienda_ropa.model.ProveedoresModel;
import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.services.ProveedoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/proveedores")
@CrossOrigin("*")
public class ProveedoresControlador {
    @Autowired
    ProveedoresServices proveedoresServices;
    @GetMapping("/todos")
    public List<ProveedoresModel> obtenerTodo(){
        return proveedoresServices.obtenerTodosProveedores();
    }
    @GetMapping("{id_proveedor}/productos")
    public ResponseEntity<List<ProductosModel>> obtenerProductosPorProveedor(@PathVariable Long id_proveedor){
        List<ProductosModel> productos = proveedoresServices.obtenerProductosPorProveedor(id_proveedor);
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/crear")
    public ProveedoresModel crear(@RequestBody ProveedoresModel proveedores){
        return proveedoresServices.crearProveedores(proveedores);
    }
    @PutMapping("/actualizar")
    public ProveedoresModel actualizar(@RequestBody ProveedoresModel proveedores){
        return proveedoresServices.actualizarProveedores(proveedores);
    }
    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        proveedoresServices.borrarProveedores(id);
    }
}
