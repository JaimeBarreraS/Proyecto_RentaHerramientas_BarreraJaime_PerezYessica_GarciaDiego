package com.proyecto.domain.DTOs;

public class UsuariosRequestDTO {
    private String nombre_usuario;
    private Long usuario_ID;

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public Long getUsuario_ID() {
        return usuario_ID;
    }
    
    public void setUsuario_ID(Long usuario_ID) {
        this.usuario_ID = usuario_ID;
    }
}
