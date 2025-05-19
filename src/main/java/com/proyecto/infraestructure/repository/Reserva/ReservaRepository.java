package com.proyecto.infraestructure.repository.Reserva;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.domain.entities.Reserva;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByClienteId(Long clienteId);
    List<Reserva> findByHerramientaProveedorId(Long proveedorId);
    List<Reserva> findByEstado(Reserva.Estado estado);
}
