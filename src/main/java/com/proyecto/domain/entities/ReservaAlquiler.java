package com.proyecto.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ReservaAlquiler")
public class ReservaAlquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fechaInicio;
    private String fechaFin;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private HerramientaEquipo herramientaEquipo;

    private String estado;

    public ReservaAlquiler() {
    }

    public ReservaAlquiler(Long id, String fechaInicio, String fechaFin, Cliente cliente,
            HerramientaEquipo herramientaEquipo, String estado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.herramientaEquipo = herramientaEquipo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public HerramientaEquipo getHerramientaEquipo() {
        return herramientaEquipo;
    }

    public void setHerramientaEquipo(HerramientaEquipo herramientaEquipo) {
        this.herramientaEquipo = herramientaEquipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
