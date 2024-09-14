package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServices {

    @Autowired
    VentasRepository ventasRepository;

    public List<VentasModel> obtenerTodosVentas() {
        return ventasRepository.findAll();
    }

    public VentasModel crearVentas(VentasModel ventas){
        return ventasRepository.save(ventas);
    }

    public VentasModel actualizarVentas(VentasModel ventas){
        if (ventas.getIdVenta() != null){
            return ventasRepository.save(ventas);
        }return null;
    }

    public void borrarVentas(Long id){
        ventasRepository.deleteById(id);
    }

}
