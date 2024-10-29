package com.example.tienda_ropa.repository;

import com.example.tienda_ropa.model.ProductosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<ProductosModel,Long> {



}
