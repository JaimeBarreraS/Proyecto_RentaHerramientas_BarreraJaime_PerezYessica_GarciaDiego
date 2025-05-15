package com.proyecto.infraestructure.repository.Usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto.application.service.IUsuarios;
import com.proyecto.domain.entities.Usuarios;

@Service
public class UsuariosImpl implements IUsuarios{

    private UsuariosRepository usuariosRepository;
    

    public UsuariosImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<Usuarios> findAll() {
        return(List<Usuarios>) usuariosRepository.findAll();
    }

    @Override
    public Optional<Usuarios> findById(Long id){
        return usuariosRepository.findById(id);
    }

    @Override
    public Usuarios save(Usuarios usuarios) {
        return usuariosRepository.save(usuarios);
    }

    @Override
    public Optional<Usuarios> update(Long id, Usuarios usuarios) {
        Optional<Usuarios> usuariod = usuariosRepository.findById(id);
        if (usuariod.isPresent()){
            Usuarios usuariodb = usuariod.orElseThrow();
            usuariodb.setNombre_usuario(usuarios.getNombre_usuario());
            return Optional.of(usuariosRepository.save(usuarios));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuarios> delete(Long id) {
        Optional<Usuarios> usuOptional = usuariosRepository.findById(id);
        usuOptional.ifPresent(usuariodb -> {
            usuariosRepository.delete(usuariodb);
        });
        return Optional.empty();
    }
}
