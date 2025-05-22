package com.proyecto.domain.DTOs;


import com.proyecto.domain.entities.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    
    @Email
    @NotBlank
    private String email;

    private String password;
    
    @NotBlank
    private String nombre;
    
    private String telefono;
    
    private Usuario.Role role;
    
    private boolean activo;


}
