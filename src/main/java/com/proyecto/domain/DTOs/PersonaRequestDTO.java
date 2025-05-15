package com.proyecto.domain.DTOs;

public class PersonaRequestDTO {
    private String nombre;
    private Long herramienta_ID;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getHerramienta_ID() {
        return herramienta_ID;
    }
    
    public void setHerramienta_ID(Long herramienta_ID) {
        this.herramienta_ID = herramienta_ID;
    }
    
}
