package com.proyecto.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "herramientas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Herramienta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String nombre;
    
    private String descripcion;
    
    @NotBlank
    private String categoria;
    
    @Positive
    @Column(name = "precio_dia")
    private BigDecimal precioPorDia;
    
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.DISPONIBLE;
    
    @Column(name = "imagen_url")
    private String imagenUrl;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Usuario proveedor;

    @OneToMany(mappedBy = "herramienta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    
    public enum Estado {
        DISPONIBLE, ALQUILADA, MANTENIMIENTO, NO_DISPONIBLE
    }
}
