package com.proyecto.infraestructure.repository.Pago;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.domain.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {

}
