package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.ReporteService;
import com.proyecto.application.service.UsuarioService;
import com.proyecto.domain.entities.ReporteDano;
import com.proyecto.domain.entities.Usuario;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/reportes")
    public List<ReporteDano> obtenerReportes() {
        return reporteService.obtenerTodosLosReportes();
    }

    @GetMapping("/test")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("Hola ADMIN, acceso autorizado.");
    }
}
