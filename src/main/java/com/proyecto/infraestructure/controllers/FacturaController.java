package com.proyecto.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.application.service.FacturaService;
import com.proyecto.domain.entities.Factura;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturaService.obtenerTodas();
    }

    @PostMapping
    public Factura generar(@RequestBody Factura factura) {
        return facturaService.generarFactura(factura);
    }
}
