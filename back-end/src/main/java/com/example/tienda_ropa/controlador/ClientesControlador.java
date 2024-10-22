package com.example.tienda_ropa.controlador;

import com.example.tienda_ropa.model.ClientesModel;
import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.services.ClientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
@CrossOrigin("*")
public class ClientesControlador {

@Autowired
    ClientesServices clientesServices;

    @GetMapping("/todos")
    public List<ClientesModel> obtenerTodo(){
        return clientesServices.obtenerTodosClientes();
    }


    @GetMapping("{id_cliente}/compras")
    public ResponseEntity<List<VentasModel>> obtenerListaVentasProducto(@PathVariable Long id_cliente){
        List<VentasModel> compras = clientesServices.obtenerComprasRealizadas(id_cliente);
        return ResponseEntity.ok(compras);
    }

    @PostMapping("/crear")
    public ClientesModel crear(@RequestBody ClientesModel clientes){
        return clientesServices.crearClientes(clientes);
    }

    @PutMapping("/actualizar")
    public ClientesModel actualizar(@RequestBody ClientesModel clientes){
        return clientesServices.actualizarClientes(clientes);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        clientesServices.borrarClientes(id);
    }


}
