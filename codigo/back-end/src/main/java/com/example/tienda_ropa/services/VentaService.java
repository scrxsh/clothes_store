package com.example.tienda_ropa.services;

import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.repositories.ClienteRepository;
import com.example.tienda_ropa.repositories.EmpleadoRepository;
import com.example.tienda_ropa.repositories.ProductoRepository;
import com.example.tienda_ropa.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ProductoRepository productosRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<VentaModel> obtenerTodosVentas() {
        return ventaRepository.findAll();
    }

    public VentaModel obtenerVentaPorId(Long id){
        return ventaRepository.findById(id).orElse(null);
    }

    public VentaModel crearVentas(VentaModel venta, Long id_producto, Long id_cliente, Long id_empleado){
        venta.setProducto(productosRepository.findById(id_producto).orElse(null));
        venta.setCliente(clienteRepository.findById(id_cliente).orElse(null));
        venta.setEmpleado(empleadoRepository.findById(id_empleado).orElse(null));
        return ventaRepository.save(venta);
    }

    public VentaModel actualizarVentas(VentaModel ventas){
        if (ventas.getId() != null){
            return ventaRepository.save(ventas);
        }return null;
    }


    public void borrarVentas(Long id){
        ventaRepository.deleteById(id);
    }


}
