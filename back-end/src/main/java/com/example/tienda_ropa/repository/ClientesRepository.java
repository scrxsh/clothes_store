package com.example.tienda_ropa.repository;

import com.example.tienda_ropa.model.ClientesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesModel,Long> {




}
