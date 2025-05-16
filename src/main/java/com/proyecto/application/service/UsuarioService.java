package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.entities.Usuario;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Usuario obtenerPorEmail(String email);
    List<Usuario> obtenerTodos();
}
