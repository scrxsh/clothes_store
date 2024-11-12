package com.example.tienda_ropa.repositories;

import com.example.tienda_ropa.models.ProveedorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<ProveedorModel,Long> {
}
