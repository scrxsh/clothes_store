package com.example.tienda_ropa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class ProveedoresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200, name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(nullable = false, length = 100)
    private String producto;
    @Column(nullable = false, length = 100)
    private String encargado;
    @Column(nullable = false)
    private Long telefono;
    @Column(nullable = false, length = 150)
    private String correo;
    @Column(nullable = false, length = 100)
    private String direccion;

}
