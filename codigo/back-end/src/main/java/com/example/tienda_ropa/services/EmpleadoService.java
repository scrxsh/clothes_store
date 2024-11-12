package com.example.tienda_ropa.services;

import com.example.tienda_ropa.models.EmpleadoModel;
import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<EmpleadoModel> obtenerTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    public List<VentaModel> obtenerVentasRealizadas(Long id_empleado){
        EmpleadoModel empleado = empleadoRepository.findById(id_empleado).orElse(null);
        return Objects.requireNonNull(empleado).getVentasRealizadas();
    }

    public EmpleadoModel crearEmpleados(EmpleadoModel empleados){
        return empleadoRepository.save(empleados);
    }

    public EmpleadoModel actualizarEmpleados(EmpleadoModel empleados){
        if (empleados.getId() != null){
            return empleadoRepository.save(empleados);
        }return null;
    }

    public void borrarEmpleados(Long id){
        empleadoRepository.deleteById(id);
    }

}
