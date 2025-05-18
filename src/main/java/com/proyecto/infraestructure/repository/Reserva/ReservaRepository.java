package com.proyecto.infraestructure.repository.Reserva;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.domain.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByHerramientaProveedorId(Long proveedorId);

}
