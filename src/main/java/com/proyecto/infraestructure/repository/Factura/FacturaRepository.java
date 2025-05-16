package com.proyecto.infraestructure.repository.Factura;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.domain.entities.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
