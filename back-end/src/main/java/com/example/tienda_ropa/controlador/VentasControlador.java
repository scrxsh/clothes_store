package com.example.tienda_ropa.controlador;

import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.services.VentasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ventas")
@CrossOrigin("*")
public class VentasControlador {

    @Autowired
    VentasServices ventasServices;

    @GetMapping("/todos")
    public List<VentasModel> obtenerTodo(){
        return ventasServices.obtenerTodosVentas();
    }

    @PostMapping("/crear")
    public VentasModel crear(@RequestBody VentasModel ventas){
        return ventasServices.crearVentas(ventas);
    }

    @PutMapping("/actualizar")
    public VentasModel actualizar(@RequestBody VentasModel ventas){
        return ventasServices.actualizarVentas(ventas);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        ventasServices.borrarVentas(id);
    }



}
