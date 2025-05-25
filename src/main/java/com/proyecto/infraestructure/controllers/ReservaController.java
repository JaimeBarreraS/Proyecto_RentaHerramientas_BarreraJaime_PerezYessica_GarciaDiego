package com.proyecto.infraestructure.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.proyecto.application.service.ReservaService;
import com.proyecto.domain.DTOs.ReservaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Gestión de reservas")
@CrossOrigin
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR', 'CLIENTE')")
    @Operation(summary = "Obtener todas las reservas")
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR', 'CLIENTE')")
    @Operation(summary = "Obtener reserva por ID")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR', 'CLIENTE')")
    @Operation(summary = "Crear reserva")
    public ResponseEntity<ReservaDTO> createReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.save(reservaDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR', 'CLIENTE')")
    @Operation(summary = "Actualizar reserva")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.update(id, reservaDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR', 'CLIENTE')")
    @Operation(summary = "Eliminar reserva")
    public ResponseEntity<?> deleteReserva(@PathVariable Long id) {
        reservaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/confirmar")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR', 'CLIENTE')")
    @Operation(summary = "Confirmar reserva")
    public ResponseEntity<ReservaDTO> confirmarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.confirmarReserva(id));
    }

    @PutMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR', 'CLIENTE')")
    @Operation(summary = "Cancelar reserva")
    public ResponseEntity<ReservaDTO> cancelarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelarReserva(id));
    }
}
