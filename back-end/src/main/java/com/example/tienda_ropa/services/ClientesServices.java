package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.ClientesModel;
import com.example.tienda_ropa.model.ProductosModel;
import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClientesServices {

@Autowired
    ClientesRepository clientesRepository;

    public List<ClientesModel> obtenerTodosClientes() {
        return clientesRepository.findAll();
    }


    public List<VentasModel> obtenerComprasRealizadas(Long id_cliente){
        ClientesModel cliente = clientesRepository.findById(id_cliente).orElse(null);
        return cliente.getComprasCliente();
    }


    public ClientesModel crearClientes(ClientesModel clientes){
        return clientesRepository.save(clientes);
    }

    public ClientesModel actualizarClientes(ClientesModel clientes){
        if (clientes.getId() != null){
            return clientesRepository.save(clientes);
        }return null;
    }

    public void borrarClientes(Long id){
        clientesRepository.deleteById(id);
    }

}
