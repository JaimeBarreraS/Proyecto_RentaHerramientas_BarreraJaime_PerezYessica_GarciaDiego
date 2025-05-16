package com.proyecto.infraestructure.repository.Reporte;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.domain.entities.ReporteDano;

public interface ReporteRepository extends JpaRepository<ReporteDano, Long> {

}