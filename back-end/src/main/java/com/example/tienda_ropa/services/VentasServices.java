package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.repository.ClientesRepository;
import com.example.tienda_ropa.repository.EmpleadosRepository;
import com.example.tienda_ropa.repository.ProductosRepository;
import com.example.tienda_ropa.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServices {

    @Autowired
    VentasRepository ventasRepository;
    @Autowired
    ProductosRepository productosRepository;
    @Autowired
    ClientesRepository clientesRepository;
    @Autowired
    EmpleadosRepository empleadosRepository;

    public List<VentasModel> obtenerTodosVentas() {
        return ventasRepository.findAll();
    }

    public VentasModel crearVentas(VentasModel venta, Long id_producto, Long id_cliente, Long id_empleado){
        venta.setProducto(productosRepository.findById(id_producto).orElse(null));
        venta.setCliente(clientesRepository.findById(id_cliente).orElse(null));
        venta.setEmpleado(empleadosRepository.findById(id_empleado).orElse(null));
        return ventasRepository.save(venta);
    }

    public VentasModel actualizarVentas(VentasModel ventas){
        if (ventas.getId() != null){
            return ventasRepository.save(ventas);
        }return null;
    }

    public void borrarVentas(Long id){
        ventasRepository.deleteById(id);
    }

}
