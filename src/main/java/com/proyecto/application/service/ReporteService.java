package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.entities.ReporteDano;

public interface ReporteService {
    List<ReporteDano> obtenerTodosLosReportes();
    ReporteDano registrarReporte(ReporteDano reporte);
}
