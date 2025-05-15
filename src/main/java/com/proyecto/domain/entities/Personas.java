package com.proyecto.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long persona_ID;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_ID", referencedColumnName = "usuario_ID")
    private Usuarios usuario;

    public Personas() {
    }
    
    public Personas(Long persona_ID, String nombre, String apellido, String correo, String telefono, String direccion, Usuarios usuario) {
        this.persona_ID = persona_ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
    }

    public Long getPersona_ID() {
        return persona_ID;
    }

    public void setPersona_ID(Long persona_ID) {
        this.persona_ID = persona_ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
}
