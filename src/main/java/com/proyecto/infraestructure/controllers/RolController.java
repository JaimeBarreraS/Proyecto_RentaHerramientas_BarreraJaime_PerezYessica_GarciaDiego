package com.proyecto.infraestructure.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.proyecto.application.service.IRol;
import com.proyecto.domain.DTOs.RolRequestDTO;
import com.proyecto.domain.entities.Rol;
import com.proyecto.domain.entities.Usuarios;
import com.proyecto.infraestructure.repository.Usuarios.UsuariosRepository;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    private IRol role;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping
    public List<Rol> list(){
        return role.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Rol> rolOptional = role.findById(id);
        if(rolOptional.isPresent()){
            return ResponseEntity.ok(rolOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RolRequestDTO rolRequestDTO){
        Usuarios usuario = usuariosRepository.findById(rolRequestDTO.getUsuario_ID())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = new Rol();
        rol.setNombre(rolRequestDTO.getNombre_usuario());
        rol.setUsuario(usuario);
        Rol savedRol = role.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Rol rol){
        Optional<Rol> rolOptional = role.findById(id);
        if(rolOptional.isPresent()){
            Rol updateRol = rolOptional.orElseThrow();
            updateRol.setNombre(rol.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(role.save(updateRol));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Rol> rolOptional = role.delete(id);
        if(rolOptional.isPresent()){
            return ResponseEntity.ok(rolOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
