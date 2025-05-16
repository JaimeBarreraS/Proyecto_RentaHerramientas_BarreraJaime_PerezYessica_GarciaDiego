package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.PagoService;
import com.proyecto.domain.entities.Pago;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<Pago> obtenerTodos() {
        return pagoService.obtenerTodos();
    }

    @PostMapping
    public Pago procesarPago(@RequestBody Pago pago) {
        return pagoService.procesarPago(pago);
    }
}
