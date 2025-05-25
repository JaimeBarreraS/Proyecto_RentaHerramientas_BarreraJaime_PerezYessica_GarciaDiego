package com.proyecto.infraestructure.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.proyecto.application.service.HerramientaService;
import com.proyecto.domain.DTOs.HerramientaDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@Tag(name = "Herramientas", description = "Gestión de herramientas")
@CrossOrigin
public class HerramientaController {

    @Autowired
    private HerramientaService herramientaService;

    @GetMapping
    @Operation(summary = "Obtener todas las herramientas")
    public ResponseEntity<List<HerramientaDTO>> getAllHerramientas() {
        return ResponseEntity.ok(herramientaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener herramienta por ID")
    public ResponseEntity<HerramientaDTO> getHerramientaById(@PathVariable Long id) {
        return ResponseEntity.ok(herramientaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR')")
    @Operation(summary = "Crear herramienta")
    public ResponseEntity<HerramientaDTO> createHerramienta(@Valid @RequestBody HerramientaDTO herramientaDTO) {
        return ResponseEntity.ok(herramientaService.save(herramientaDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR')")
    @Operation(summary = "Actualizar herramienta")
    public ResponseEntity<HerramientaDTO> updateHerramienta(@PathVariable Long id, @Valid @RequestBody HerramientaDTO herramientaDTO) {
        System.out.println(">>> Entró al método PUT");
        return ResponseEntity.ok(herramientaService.update(id, herramientaDTO));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR')")
    @Operation(summary = "Eliminar herramienta")
    public ResponseEntity<?> deleteHerramienta(@PathVariable Long id) {
        herramientaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtener herramientas por categoría")
    public ResponseEntity<List<HerramientaDTO>> getHerramientasByCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(herramientaService.findByCategoria(categoria));
    }

    @GetMapping("/proveedor/{proveedorId}")
    @Operation(summary = "Obtener herramientas de un proveedor")
    public ResponseEntity<List<HerramientaDTO>> getHerramientasByProveedor(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(herramientaService.findByProveedorId(proveedorId));
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Obtener herramientas disponibles en un rango de fechas")
    public ResponseEntity<List<HerramientaDTO>> getHerramientasDisponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(herramientaService.findHerramientasDisponibles(fechaInicio, fechaFin));
    }

    @GetMapping("/categorias")
    @Operation(summary = "Obtener lista de categorías disponibles")
    public ResponseEntity<List<String>> getCategorias() {
        return ResponseEntity.ok(herramientaService.findCategorias());
    }
}
