package com.proyecto.application.service;

import com.proyecto.domain.DTOs.HerramientaDTO;
import java.time.LocalDate;
import java.util.List;

public interface HerramientaService {
    List<HerramientaDTO> findAll();
    HerramientaDTO findById(Long id);
    HerramientaDTO save(HerramientaDTO herramientaDTO);
    HerramientaDTO update(Long id, HerramientaDTO herramientaDTO);
    void delete(Long id);
    List<HerramientaDTO> findByCategoria(String categoria);
    List<HerramientaDTO> findByProveedorId(Long proveedorId);
    List<HerramientaDTO> findHerramientasDisponibles(LocalDate fechaInicio, LocalDate fechaFin);
    List<String> findCategorias();
}