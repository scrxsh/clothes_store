package com.example.tienda_ropa.controlador;

import com.example.tienda_ropa.model.EmpleadosModel;
import com.example.tienda_ropa.services.EmpleadosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empleados")
@CrossOrigin("*")
public class EmpleadosControlador {

    @Autowired
    EmpleadosServices empleadosServices;

    @GetMapping("/todos")
    public List<EmpleadosModel> obtenerTodo(){
        return empleadosServices.obtenerTodosEmpleados();
    }

    @PostMapping("/crear")
    public EmpleadosModel crear(@RequestBody EmpleadosModel empleados){
        return empleadosServices.crearEmpleados(empleados);
    }

    @PutMapping("/actualizar")
    public EmpleadosModel actualizar(@RequestBody EmpleadosModel empleados){
        return empleadosServices.actualizarEmpleados(empleados);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        empleadosServices.borrarEmpleados(id);
    }





}
