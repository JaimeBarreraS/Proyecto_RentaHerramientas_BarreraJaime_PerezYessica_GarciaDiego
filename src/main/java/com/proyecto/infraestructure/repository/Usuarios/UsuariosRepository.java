package com.proyecto.infraestructure.repository.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.domain.entities.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{

}
