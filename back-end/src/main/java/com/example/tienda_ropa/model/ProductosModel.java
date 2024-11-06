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
@Table(name = "productos")
public class ProductosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "img_prenda")
    private String imgPrenda;
    @Column(nullable = false, length = 45)
    private String nombre;
    @Column(nullable = false, length = 10)
    private String color;
    @Column(length = 100)
    private String descripcion;
    @Column(nullable = false)
    private float precio;
    @Column(nullable = false, name = "p_iva")
    private float pIva;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false, length = 20)
    private String tipo;
    @Column(nullable = false, length = 1)
    private char talla;
    @Column(name = "precio_final", nullable = false)
    private Float precioFinal;

    @PrePersist
    @PreUpdate
    void calculoPrecioFinal(){
        this.precioFinal = precio + (precio * pIva);
    }

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    ProveedoresModel proveedor;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    List<VentasModel> ventasList;

}
