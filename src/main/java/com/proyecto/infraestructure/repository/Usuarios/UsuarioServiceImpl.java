package com.proyecto.infraestructure.repository.Usuarios;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.UsuarioService;
import com.proyecto.domain.DTOs.UsuarioDTO;
import com.proyecto.domain.entities.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return convertToDTO(usuario);
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        return convertToDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        Usuario usuario = convertToEntity(usuarioDTO);
        usuario.setId(id);
        usuario.setPassword(existingUsuario.getPassword()); // Mantener contraseña existente

        return convertToDTO(usuarioRepository.save(usuario));
    }

    @Override
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioDTO> findByRole(Usuario.Role role) {
        return usuarioRepository.findByRole(role).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con email: " + email));
        return convertToDTO(usuario);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        BeanUtils.copyProperties(usuario, dto);
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        return usuario;
    }
}