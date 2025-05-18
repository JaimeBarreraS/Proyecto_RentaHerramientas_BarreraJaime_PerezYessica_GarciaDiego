package com.proyecto.infraestructure.repository.Reserva;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.ReservaService;
import com.proyecto.domain.entities.Reserva;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> obtenerReservasPorProveedor(Long proveedorId) {
        return reservaRepository.findByHerramientaProveedorId(proveedorId);
    }


    @Override
    public Reserva obtenerPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

}
