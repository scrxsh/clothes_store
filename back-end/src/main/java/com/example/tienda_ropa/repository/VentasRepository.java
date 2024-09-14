package com.example.tienda_ropa.repository;

import com.example.tienda_ropa.model.VentasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends JpaRepository<VentasModel,Long> {
}
