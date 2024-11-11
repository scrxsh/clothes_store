package com.example.tienda_ropa.repositories;

import com.example.tienda_ropa.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoModel,Long> {



}
