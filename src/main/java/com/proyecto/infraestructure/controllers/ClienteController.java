package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.HerramientaService;
import com.proyecto.application.service.ReservaService;
import com.proyecto.domain.entities.Herramienta;
import com.proyecto.domain.entities.Reserva;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private HerramientaService herramientaService;

    @PostMapping("/reservar")
    public Reserva realizarReserva(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }

    @GetMapping("/herramientas")
    public List<Herramienta> listarHerramientasDisponibles() {
        return herramientaService.obtenerHerramientasDisponibles();
    }
}
