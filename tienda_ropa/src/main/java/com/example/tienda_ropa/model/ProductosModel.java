package com.example.tienda_ropa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Productos")
public class ProductosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductos;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false)
    private int precio;
    @Column(nullable = false)
    private Byte iva;
    @Column(nullable = false)
    private int unidades;
    @Column(nullable = false, length = 100)
    private String tipo;
    @Column(nullable = false, length = 100)
    private String talla;
    @Column(nullable = false)
    private Long codigoPrenda;

}
