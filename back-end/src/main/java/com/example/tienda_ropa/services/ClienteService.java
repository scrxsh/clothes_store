package com.example.tienda_ropa.services;

import com.example.tienda_ropa.models.ClienteModel;
import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

@Autowired
ClienteRepository clienteRepository;

    public List<ClienteModel> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    public ClienteModel obtenerPorId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public List<VentaModel> obtenerComprasRealizadas(Long id_cliente){
        ClienteModel cliente = clienteRepository.findById(id_cliente).orElse(null);
        return Objects.requireNonNull(cliente).getComprasCliente();
    }


    public ClienteModel crearClientes(ClienteModel clientes){
        return clienteRepository.save(clientes);
    }

    public ClienteModel actualizarClientes(ClienteModel clientes){
        if (clientes.getId() != null){
            return clienteRepository.save(clientes);
        }return null;
    }

    public void borrarClientes(Long id){
        clienteRepository.deleteById(id);
    }

}
