package com.proyecto.application.service;

import java.util.List;
import java.util.Optional;
import com.proyecto.domain.entities.Usuarios;
public interface IUsuarios {
    List<Usuarios> findAll();

    Optional<Usuarios> findById(Long id);

    Usuarios save(Usuarios usuarios);
    
    Optional<Usuarios> update(Long id, Usuarios usuarios);

    Optional<Usuarios> delete(Long id);
}
