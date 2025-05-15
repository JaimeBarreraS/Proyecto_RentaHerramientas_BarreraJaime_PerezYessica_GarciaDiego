package com.proyecto.application.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.domain.entities.Rol;

public interface IRol {
    List<Rol> findAll();

    Optional<Rol> findById(Long id);

    Rol save(Rol rol);

    Optional<Rol> update(Long id, Rol rol);

    Optional<Rol> delete(Long id);
} 
