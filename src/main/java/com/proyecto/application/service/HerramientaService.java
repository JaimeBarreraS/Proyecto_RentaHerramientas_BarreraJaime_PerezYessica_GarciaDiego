package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.entities.Herramienta;

public interface HerramientaService {
    Herramienta agregarHerramienta(Herramienta herramienta);
    List<Herramienta> obtenerHerramientasDisponibles();
    List<Herramienta> obtenerTodas();
    Herramienta obtenerPorId(Long id);
}
