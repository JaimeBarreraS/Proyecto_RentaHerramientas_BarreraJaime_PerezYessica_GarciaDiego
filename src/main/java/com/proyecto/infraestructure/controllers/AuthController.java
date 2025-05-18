package com.proyecto.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.UsuarioService;
import com.proyecto.domain.entities.Usuario;
import com.proyecto.infraestructure.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuarioService.obtenerPorEmail(usuario.getEmail()) != null) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }
        Usuario nuevo = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario login) {
        Usuario usuario = usuarioService.obtenerPorEmail(login.getEmail());
        if (usuario == null || !usuario.getPassword().equals(login.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol().name());
        return ResponseEntity.ok(token);
    }
}
