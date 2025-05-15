package com.proyecto.application.service;

import java.util.List;
import java.util.Optional;
import com.proyecto.domain.entities.Personas;

public interface IPersona {
    List<Personas> findAll();

    Optional<Personas> findById(Long id);

    Personas save(Personas personas);
    
    Optional<Personas> update(Long id, Personas personas);

    Optional<Personas> delete(Long id);
}
