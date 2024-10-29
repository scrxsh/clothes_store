package com.example.tienda_ropa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ventas")
public class VentasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int precio;
    @Column(nullable = false)
    private Byte iva;
    @Column(nullable = false)
    private int unidades;
    @Column(nullable = false)
    private Long total;
    @Column (nullable = false, name = "fecha_venta")
    private Date fechaVenta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    ProductosModel producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    ClientesModel cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    EmpleadosModel empleado;
}
