package com.proyecto.domain.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.proyecto.domain.entities.Herramienta;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HerramientaDTO {
    private Long id;
    
    @NotBlank
    private String nombre;
    
    private String descripcion;
    
    @NotBlank
    private String categoria;
    
    @Positive
    private BigDecimal precioPorDia;
    
    private Herramienta.Estado estado;
    
    private String imagenUrl;
    
    private Long proveedorId;
    
    private String proveedorNombre;
}
