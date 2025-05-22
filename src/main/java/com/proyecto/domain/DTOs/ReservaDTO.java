package com.proyecto.domain.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.proyecto.domain.entities.Reserva;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private Long id;
    
    @NotNull
    private Long clienteId;
    
    @NotNull
    private Long herramientaId;
    
    @NotNull
    private LocalDate fechaInicio;
    
    @NotNull
    private LocalDate fechaFin;
    
    private BigDecimal totalPago;
    
    private Reserva.Estado estado;
    
    private String observaciones;

    private LocalDateTime fechaReserva;
    
    // Campos adicionales para vista
    private String clienteNombre;
    private String herramientaNombre;
    private String herramientaImagen;

}