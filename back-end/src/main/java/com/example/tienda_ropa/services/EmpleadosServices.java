package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.EmpleadosModel;
import com.example.tienda_ropa.repository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadosServices {

    @Autowired
    EmpleadosRepository empleadosRepository;

    public List<EmpleadosModel> obtenerTodosEmpleados() {
        return empleadosRepository.findAll();
    }

    public EmpleadosModel crearEmpleados(EmpleadosModel empleados){
        return empleadosRepository.save(empleados);
    }

    public EmpleadosModel actualizarEmpleados(EmpleadosModel empleados){
        if (empleados.getId() != null){
            return empleadosRepository.save(empleados);
        }return null;
    }

    public void borrarEmpleados(Long id){
        empleadosRepository.deleteById(id);
    }

}
