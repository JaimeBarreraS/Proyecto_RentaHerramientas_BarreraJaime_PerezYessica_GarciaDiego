package com.proyecto.infraestructure.repository.Pago;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.PagoService;
import com.proyecto.domain.entities.Pago;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public Pago procesarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }
}
