package com.proyecto.infraestructure.repository.Pago;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.domain.entities.Pago;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findByReservaId(Long reservaId);
    List<Pago> findByEstado(Pago.Estado estado);
}