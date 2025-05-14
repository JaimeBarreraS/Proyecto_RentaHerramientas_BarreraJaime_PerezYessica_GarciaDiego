package com.proyecto.domain.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rol_ID;
    private String nombre;

    @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Usuarios> usuarios;

    public Rol() {
    }

    public Rol(Long rol_ID, String nombre) {
        this.rol_ID = rol_ID;
        this.nombre = nombre;
    }

    public Long getRol_ID() {
        return rol_ID;
    }
    public void setRol_ID(Long rol_ID) {
        this.rol_ID = rol_ID;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
