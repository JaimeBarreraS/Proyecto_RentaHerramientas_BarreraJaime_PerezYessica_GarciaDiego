package com.proyecto.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "herramienta_id")
    private Herramienta herramienta;
    
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
    
    @Column(name = "total_pago")
    private BigDecimal totalPago;
    
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;
    
    @Column(name = "fecha_reserva")
    private LocalDateTime fechaReserva;
    
    private String observaciones;
    
    public enum Estado {
        PENDIENTE, CONFIRMADA, EN_CURSO, COMPLETADA, CANCELADA
    }
}

