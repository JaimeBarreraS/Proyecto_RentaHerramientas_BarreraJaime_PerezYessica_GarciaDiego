package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.entities.Pago;

public interface PagoService {
    Pago procesarPago(Pago pago);
    List<Pago> obtenerTodos();
}