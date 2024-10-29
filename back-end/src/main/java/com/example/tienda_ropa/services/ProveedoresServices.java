package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.EmpleadosModel;
import com.example.tienda_ropa.model.ProductosModel;
import com.example.tienda_ropa.model.ProveedoresModel;
import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.repository.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProveedoresServices {

    @Autowired
    ProveedoresRepository proveedoresRepository;

    public List<ProveedoresModel> obtenerTodosProveedores() {
        return proveedoresRepository.findAll();
    }
    public List<ProductosModel> obtenerProductosPorProveedor(Long id_proovedor){
        ProveedoresModel proveedor = proveedoresRepository.findById(id_proovedor).orElse(null);
        return Objects.requireNonNull(proveedor).getProductsList();
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
