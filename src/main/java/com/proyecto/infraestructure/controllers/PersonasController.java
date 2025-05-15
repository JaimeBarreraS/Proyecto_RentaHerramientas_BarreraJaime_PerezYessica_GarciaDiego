package com.proyecto.infraestructure.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.IPersona;
import com.proyecto.domain.DTOs.PersonaRequestDTO;
import com.proyecto.domain.entities.Personas;
import com.proyecto.infraestructure.repository.Personas.PersonaRepository;

@RestController
@RequestMapping("/api/persona")
public class PersonasController {
    @Autowired
    private IPersona persona;

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public List<Personas> list(){
        return persona.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Personas> perOptional = persona.findById(id);
        if(perOptional.isPresent()){
            return ResponseEntity.ok(perOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

   /* @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonaRequestDTO personaRequestDTO){
        Personas personas = personaRepository.findById(personaRequestDTO.getHerramienta_ID())
        .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Personas per = new Personas();
        per.setNombre(personaRequestDTO.getNombre());
        per.set
    }*/
}
