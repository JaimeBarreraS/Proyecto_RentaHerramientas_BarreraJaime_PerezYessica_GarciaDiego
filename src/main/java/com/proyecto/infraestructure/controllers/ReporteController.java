package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.ReporteService;
import com.proyecto.domain.entities.ReporteDano;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<ReporteDano> listar() {
        return reporteService.obtenerTodosLosReportes();
    }

    @PostMapping
    public ReporteDano crear(@RequestBody ReporteDano reporte) {
        return reporteService.registrarReporte(reporte);
    }
}
