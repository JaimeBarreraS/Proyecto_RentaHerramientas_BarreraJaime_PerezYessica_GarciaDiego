package com.proyecto.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "herramientas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Herramienta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    private Double precioAlquiler;

    private Boolean disponible;

    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Usuario proveedor;

    @OneToMany(mappedBy = "herramienta", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "herramienta", cascade = CascadeType.ALL)
    private List<ReporteDano> reportesDano;

}
