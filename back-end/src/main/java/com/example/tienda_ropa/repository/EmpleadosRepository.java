package com.example.tienda_ropa.repository;

import com.example.tienda_ropa.model.EmpleadosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosRepository extends JpaRepository<EmpleadosModel,Long> {



}
