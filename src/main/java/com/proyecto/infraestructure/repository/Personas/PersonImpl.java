package com.proyecto.infraestructure.repository.Personas;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.proyecto.application.service.IPersona;
import com.proyecto.domain.entities.HerramientaEquipo;
import com.proyecto.domain.entities.Personas;

@Service
public class PersonImpl implements IPersona{

    private PersonaRepository personaRepository;

    public PersonImpl(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Personas> findAll() {
        return(List<Personas>) personaRepository.findAll();
    }

    @Override
    public Optional<Personas> findById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Personas save(Personas personas) {
        return personaRepository.save(personas);
    }

    @Override
    public Optional<Personas> update(Long id, Personas personas) {
        Optional<Personas> personad = personaRepository.findById(id);
        if(personad.isPresent()){
            Personas personadb = personad.orElseThrow();
            personadb.setNombre(personas.getNombre());
            return Optional.of(personaRepository.save(personas));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Personas> delete(Long id) {
        Optional<Personas> perOptional = personaRepository.findById(id);
        perOptional.ifPresent(personadb -> {
            personaRepository.delete(personadb);
        });
        return Optional.empty();
    }

    
}
