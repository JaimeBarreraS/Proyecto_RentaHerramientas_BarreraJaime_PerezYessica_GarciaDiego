package com.proyecto.application.service;


import java.util.List;


import com.proyecto.domain.DTOs.UsuarioDTO;
import com.proyecto.domain.entities.Usuario;
public interface UsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findById(Long id);
    UsuarioDTO save(UsuarioDTO usuarioDTO);
    UsuarioDTO update(Long id, UsuarioDTO usuarioDTO);
    void delete(Long id);
    List<UsuarioDTO> findByRole(Usuario.Role role);
    UsuarioDTO findByEmail(String email);
}
