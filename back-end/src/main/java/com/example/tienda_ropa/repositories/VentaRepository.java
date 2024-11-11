package com.example.tienda_ropa.repositories;

import com.example.tienda_ropa.models.VentaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<VentaModel,Long> {

}
