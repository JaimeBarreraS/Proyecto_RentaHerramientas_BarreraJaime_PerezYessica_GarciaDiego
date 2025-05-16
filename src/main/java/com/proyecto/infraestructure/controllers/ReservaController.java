package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.ReservaService;
import com.proyecto.domain.entities.Reserva;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> obtenerTodas() {
        return reservaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Reserva obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id);
    }

    @PostMapping
    public Reserva crear(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }

    @GetMapping("/proveedor/{proveedorId}")
    public List<Reserva> obtenerReservasPorProveedor(@PathVariable Long proveedorId) {
        return reservaService.obtenerReservasPorProveedor(proveedorId);
    }
}
