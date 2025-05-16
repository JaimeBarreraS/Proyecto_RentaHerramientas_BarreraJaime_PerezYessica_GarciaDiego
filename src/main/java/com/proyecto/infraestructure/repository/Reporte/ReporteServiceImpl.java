package com.proyecto.infraestructure.repository.Reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.ReporteService;
import com.proyecto.domain.entities.ReporteDano;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public List<ReporteDano> obtenerTodosLosReportes() {
        return reporteRepository.findAll();
    }

    @Override
    public ReporteDano registrarReporte(ReporteDano reporte) {
        return reporteRepository.save(reporte);
    }
}
