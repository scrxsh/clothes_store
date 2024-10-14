package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.ProveedoresModel;
import com.example.tienda_ropa.repository.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresServices {

    @Autowired
    ProveedoresRepository proveedoresRepository;

    public List<ProveedoresModel> obtenerTodosProveedores() {
        return proveedoresRepository.findAll();
    }

    public ProveedoresModel crearProveedores(ProveedoresModel proveedores){
        return proveedoresRepository.save(proveedores);
    }

    public ProveedoresModel actualizarProveedores(ProveedoresModel proveedores){
        if (proveedores.getId() != null){
            return proveedoresRepository.save(proveedores);
        }return null;
    }

    public void borrarProveedores(Long id){
        proveedoresRepository.deleteById(id);
    }

}
