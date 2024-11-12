package com.example.tienda_ropa.models;

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
public class ProductoModel {

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
    private Float precio;
    @Column(nullable = false, name = "p_iva")
    private Float pIva;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false, length = 20)
    private String tipo;
    @Column(nullable = false, length = 4)
    private String talla;
    @Column(name = "precio_final", nullable = false)
    private Float precioFinal;

    @PrePersist
    @PreUpdate
    void calculoPrecioFinal(){
        this.precioFinal = precio + (precio * pIva);
    }

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    ProveedorModel proveedor;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    List<VentaModel> ventasList;

}
