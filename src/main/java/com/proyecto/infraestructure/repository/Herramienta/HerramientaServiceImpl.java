package com.proyecto.infraestructure.repository.Herramienta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.HerramientaService;
import com.proyecto.domain.entities.Herramienta;

@Service
public class HerramientaServiceImpl implements HerramientaService {

    @Autowired
    private HerramientaRepository herramientaRepository;

    @Override
    public Herramienta agregarHerramienta(Herramienta herramienta) {
        return herramientaRepository.save(herramienta);
    }

    @Override
    public List<Herramienta> obtenerHerramientasDisponibles() {
        return herramientaRepository.findByDisponibleTrue();
    }

    @Override
    public List<Herramienta> obtenerTodas() {
        return herramientaRepository.findAll();
    }

    @Override
    public Herramienta obtenerPorId(Long id) {
        return herramientaRepository.findById(id).orElse(null);
    }
}
