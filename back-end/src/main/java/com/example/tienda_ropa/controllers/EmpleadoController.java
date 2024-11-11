package com.example.tienda_ropa.controllers;

import com.example.tienda_ropa.models.EmpleadoModel;
import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empleados")
@CrossOrigin("*")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/todos")
    public List<EmpleadoModel> obtenerTodo(){
        return empleadoService.obtenerTodosEmpleados();
    }

    @GetMapping("{id_empleado}/ventas")
    public ResponseEntity<List<VentaModel>> obtenerVentasRealizadas(@PathVariable Long id_empleado){
        List<VentaModel> ventas = empleadoService.obtenerVentasRealizadas(id_empleado);
        return ResponseEntity.ok(ventas);
    }

    @PostMapping("/crear")
    public EmpleadoModel crear(@RequestBody EmpleadoModel empleados){
        return empleadoService.crearEmpleados(empleados);
    }

    @PutMapping("/actualizar")
    public EmpleadoModel actualizar(@RequestBody EmpleadoModel empleados){
        return empleadoService.actualizarEmpleados(empleados);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        empleadoService.borrarEmpleados(id);
    }





}
