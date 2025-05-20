package com.proyecto.infraestructure.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.proyecto.application.service.PagoService;
import com.proyecto.domain.DTOs.PagoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "Gestión de pagos")
@CrossOrigin
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR')")
    @Operation(summary = "Obtener todos los pagos")
    public ResponseEntity<List<PagoDTO>> getAllPagos() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pago por ID")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.findById(id));
    }

    @PostMapping("/{reservaId}/procesar")
    @PreAuthorize("hasRole('CLIENTE')")
    @Operation(summary = "Procesar pago de una reserva")
    public ResponseEntity<PagoDTO> procesarPago(@PathVariable Long reservaId, @Valid @RequestBody PagoDTO pagoDTO) {
        return ResponseEntity.ok(pagoService.procesarPago(reservaId, pagoDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pago")
    public ResponseEntity<PagoDTO> updatePago(@PathVariable Long id, @Valid @RequestBody PagoDTO pagoDTO) {
        return ResponseEntity.ok(pagoService.update(id, pagoDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar pago")
    public ResponseEntity<?> deletePago(@PathVariable Long id) {
        pagoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
