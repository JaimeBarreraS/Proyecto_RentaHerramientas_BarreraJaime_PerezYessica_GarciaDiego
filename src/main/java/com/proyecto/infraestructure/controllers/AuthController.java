package com.proyecto.infraestructure.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.proyecto.domain.DTOs.UsuarioDTO;
import com.proyecto.domain.entities.Usuario;
import com.proyecto.infraestructure.repository.Usuarios.UsuarioRepository;
import com.proyecto.infraestructure.security.JwtUtil;
import com.proyecto.infraestructure.security.UserPrincipal;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Operaciones de autenticación y registro")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userPrincipal);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("type", "Bearer");
            response.put("email", userPrincipal.getUsername());
            response.put("role", userPrincipal.getUsuario().getRole());
            response.put("id", userPrincipal.getUsuario().getId());
            response.put("nombre", userPrincipal.getUsuario().getNombre());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en el login: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario")
    public ResponseEntity<?> register(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
                return ResponseEntity.badRequest().body("Error: El email ya está en uso.");
            }

            if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Error: La contraseña no puede estar vacía.");
            }

            Usuario usuario = new Usuario();
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Se codifica correctamente
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setTelefono(usuarioDTO.getTelefono());
            usuario.setRole(usuarioDTO.getRole() != null ? usuarioDTO.getRole() : Usuario.Role.CLIENTE);
            usuario.setActivo(true);

            usuarioRepository.save(usuario);

            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en el registro: " + e.getMessage());
        }
    }
}
