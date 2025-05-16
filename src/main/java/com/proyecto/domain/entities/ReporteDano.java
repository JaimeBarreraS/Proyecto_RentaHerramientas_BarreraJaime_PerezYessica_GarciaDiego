package com.proyecto.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reportes_dano")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private LocalDateTime fechaReporte;

    @Enumerated(EnumType.STRING)
    private EstadoDano estado;

    @ManyToOne
    @JoinColumn(name = "herramienta_id")
    private Herramienta herramienta;
}
