package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.HerramientaService;
import com.proyecto.application.service.ReservaService;
import com.proyecto.domain.entities.Herramienta;
import com.proyecto.domain.entities.Reserva;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private HerramientaService herramientaService;

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/herramientas")
    public Herramienta agregarHerramienta(@RequestBody Herramienta herramienta) {
        return herramientaService.agregarHerramienta(herramienta);
    }

    @GetMapping("/reservas")
    public List<Reserva> listarReservasProveedor(@RequestParam Long proveedorId) {
        return reservaService.obtenerReservasPorProveedor(proveedorId);
    }
}
