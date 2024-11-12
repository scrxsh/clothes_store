package com.example.tienda_ropa.models;

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
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer unidades;
    @Column (nullable = false, name = "fecha_venta")
    private Date fechaVenta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    ProductoModel producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    EmpleadoModel empleado;

}
