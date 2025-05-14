package com.proyecto.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_ID;
    private String nombre_usuario;
    private String contrasena;
    private String correo;

    @ManyToOne
    @JoinColumn(name = "rol_ID", referencedColumnName = "rol_ID")
    private Rol rol;

    public Usuarios() {
    }

    public Usuarios(Long usuario_ID, String nombre_usuario, String contrasena, String correo, Rol rol_ID) {
        this.usuario_ID = usuario_ID;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.rol = rol_ID;
    }

    public Long getUsuario_ID() {
        return usuario_ID;
    }

    public void setUsuario_ID(Long usuario_ID) {
        this.usuario_ID = usuario_ID;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol_ID() {
        return rol;
    }

    public void setRol_ID(Rol rol_ID) {
        this.rol = rol_ID;
    }

}
