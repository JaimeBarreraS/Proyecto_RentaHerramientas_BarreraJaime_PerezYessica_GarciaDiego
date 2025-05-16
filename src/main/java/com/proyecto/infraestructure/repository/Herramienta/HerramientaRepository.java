package com.proyecto.infraestructure.repository.Herramienta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.domain.entities.Herramienta;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    List<Herramienta> findByDisponibleTrue();
}
