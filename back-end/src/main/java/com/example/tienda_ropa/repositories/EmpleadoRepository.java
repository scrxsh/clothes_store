package com.example.tienda_ropa.repositories;

import com.example.tienda_ropa.models.EmpleadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel,Long> {



}
