package com.example.tienda_ropa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ventas")
public class VentasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    @Column(nullable = false, length = 150)
    private String Producto;
    @Column(nullable = false)
    private int precio;
    @Column(nullable = false)
    private Byte iva;
    @Column(nullable = false)
    private int unidades;
    @Column(nullable = false)
    private Long total;

}
