package com.proyecto.infraestructure.repository.Personas;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.domain.entities.Personas;

public interface PersonaRepository extends JpaRepository<Personas, Long>{

}
