package com.proyecto.domain.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Factura")
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaEmision;
    private Double montoTotal;

    @OneToMany(mappedBy = "factura")
    private List<Pago> pagos;


    @OneToOne
    private ReservaAlquiler reservaAlquiler;

    public Factura() {
    }

    public Factura(Long id, LocalDate fechaEmision, Double montoTotal, List<Pago> pagos, ReservaAlquiler reservaAlquiler) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.pagos = pagos;
        this.reservaAlquiler = reservaAlquiler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public ReservaAlquiler getReservaAlquiler() {
        return reservaAlquiler;
    }

    public void setReservaAlquiler(ReservaAlquiler reservaAlquiler) {
        this.reservaAlquiler = reservaAlquiler;
    }

    
}
