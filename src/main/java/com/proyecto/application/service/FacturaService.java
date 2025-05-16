package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.entities.Factura;

public interface FacturaService {
    Factura generarFactura(Factura factura);
    List<Factura> obtenerTodas();
    Factura obtenerFacturaPorId(Long id);
}
