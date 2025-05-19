package com.proyecto.infraestructure.repository.Herramienta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.domain.entities.Herramienta;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    List<Herramienta> findByCategoria(String categoria);

    List<Herramienta> findByProveedorId(Long proveedorId);

    List<Herramienta> findByEstado(Herramienta.Estado estado);

    @Query("SELECT h FROM Herramienta h WHERE h.estado = 'DISPONIBLE' " +
            "AND h.id NOT IN (SELECT r.herramienta.id FROM Reserva r " +
            "WHERE r.estado IN ('CONFIRMADA', 'EN_CURSO') " +
            "AND (r.fechaInicio <= :fechaFin AND r.fechaFin >= :fechaInicio))")
    List<Herramienta> findHerramientasDisponibles(@Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}