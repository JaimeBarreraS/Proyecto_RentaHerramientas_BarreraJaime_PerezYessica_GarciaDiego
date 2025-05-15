package com.proyecto.infraestructure.repository.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.domain.entities.Rol;;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

}
