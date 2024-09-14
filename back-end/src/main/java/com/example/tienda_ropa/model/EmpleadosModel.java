package com.example.tienda_ropa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Empleados")
public class EmpleadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false)
    private Long cedula;
    @Column(nullable = false, length = 150)
    private String email;
    @Column(nullable = false)
    private Long telefono;

}
