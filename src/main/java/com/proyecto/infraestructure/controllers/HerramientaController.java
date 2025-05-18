package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.HerramientaService;
import com.proyecto.domain.entities.Herramienta;

@RestController
@RequestMapping("/herramientas")
public class HerramientaController {

    @Autowired
    private HerramientaService herramientaService;

    @GetMapping
    public List<Herramienta> listarTodas() {
        return herramientaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Herramienta obtenerPorId(@PathVariable Long id) {
        return herramientaService.obtenerPorId(id);
    }
}
