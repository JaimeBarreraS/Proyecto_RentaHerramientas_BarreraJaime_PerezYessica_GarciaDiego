package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.entities.Reserva;

public interface ReservaService {
    Reserva crearReserva(Reserva reserva);
    List<Reserva> obtenerTodas();
    List<Reserva> obtenerReservasPorProveedor(Long proveedorId);
    Reserva obtenerPorId(Long id);
    
}
