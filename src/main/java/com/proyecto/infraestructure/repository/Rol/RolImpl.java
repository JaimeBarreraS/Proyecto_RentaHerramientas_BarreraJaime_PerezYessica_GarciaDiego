package com.proyecto.infraestructure.repository.Rol;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.IRol;
import com.proyecto.domain.entities.Rol;


@Service
public class RolImpl implements IRol{
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Rol save(Rol rol) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Rol> update(Long id, Rol rol) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Rol> delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
