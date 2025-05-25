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
    
    private Long clienteId;
    
    private Long herramientaId;
    
    private LocalDate fechaInicio;
    
    private LocalDate fechaFin;
    
    private BigDecimal totalPago;
    
    private Reserva.Estado estado;
    
    private String observaciones;

    private LocalDateTime fechaReserva;
    
    private String clienteNombre;
    private String herramientaNombre;
    private String herramientaImagen;

}