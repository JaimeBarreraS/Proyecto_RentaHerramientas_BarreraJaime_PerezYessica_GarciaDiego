package com.proyecto.infraestructure.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.application.service.IRol;
import com.proyecto.application.service.IUsuarios;
import com.proyecto.domain.DTOs.RolRequestDTO;
import com.proyecto.domain.entities.Rol;
import com.proyecto.domain.entities.Usuarios;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    private IRol role;

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

    /*@PostMapping
    public ResponseEntity<?> create(@RequestBody RolRequestDTO rolRequestDTO){
        Usuarios usuarios = IUsuarios.findById(RolRequestDTO.getUsuario_ID())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = new Rol();
        Rol.setNombre(RolRequestDTO.getNombre());
        Rol.setUsuario(usuarios);

        Rol savedRol = IRol.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }*/
}
