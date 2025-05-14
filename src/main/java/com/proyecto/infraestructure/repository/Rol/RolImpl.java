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
        return rolRepository.findById(id);
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Optional<Rol> update(Long id, Rol rol) {
        Optional<Rol> rold = rolRepository.findById(id);
        if(rold.isPresent()){
            Rol roldb = rold.orElseThrow();
            roldb.setNombre(rol.getNombre());
            return Optional.of(rolRepository.save(rol));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Rol> delete(Long id) {
        Optional<Rol> rolOptional = rolRepository.findById(id);
        rolOptional.ifPresent(roldb -> {
            rolRepository.delete(roldb);
        });
        return Optional.empty();
    }

}
