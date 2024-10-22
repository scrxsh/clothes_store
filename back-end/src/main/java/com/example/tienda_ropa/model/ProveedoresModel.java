package com.example.tienda_ropa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class ProveedoresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 45, name = "nombre_empresa")//JSON PORPRETIE
    private String nombreEmpresa;
    @Column(nullable = false, length = 100)
    private String encargado;
    @Column(length = 10)
    private String telefono;
    @Column(nullable = false, length = 45)
    private String correo;
    @Column(nullable = false, length = 45)
    private String direccion;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor")
    List<ProductosModel> productsList;

}
