package com.proyecto.infraestructure.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.application.service.IUsuarios;
import com.proyecto.domain.DTOs.UsuariosRequestDTO;
import com.proyecto.domain.entities.UsuarioBasico;
import com.proyecto.domain.entities.Usuarios;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarios usuRepository;

   /*  @Autowired
    private IRol irol;*/

    @GetMapping
    public List<Usuarios> list(){
        return usuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Usuarios> usuOptional = usuRepository.findById(id);
        if(usuOptional.isPresent()){
            return ResponseEntity.ok(usuOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UsuariosRequestDTO uRequestDTO){
        Usuarios usuarioPadre = usuRepository.findById(uRequestDTO.getUsuario_ID())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Usuarios usus = new UsuarioBasico();
        usus.setNombre_usuario(uRequestDTO.getNombre_usuario());
        usus.setUsuarioPadre(usuarioPadre);

        Usuarios saveUsu = usuRepository.save(usus);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUsu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuarios usus){
        Optional<Usuarios> usuOptional = usuRepository.findById(id);
        if(usuOptional.isPresent()){
            Usuarios updateUsu = usuOptional.orElseThrow();
            updateUsu.setNombre_usuario(usus.getNombre_usuario());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuRepository.save(updateUsu));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuarios> usuOptional = usuRepository.delete(id);
        if(usuOptional.isPresent()){
            return ResponseEntity.ok(usuOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
