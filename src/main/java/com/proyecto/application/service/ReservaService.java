package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.DTOs.ReservaDTO;

public interface ReservaService {
    List<ReservaDTO> findAll();
    ReservaDTO findById(Long id);
    ReservaDTO save(ReservaDTO reservaDTO);
    ReservaDTO update(Long id, ReservaDTO reservaDTO);
    void delete(Long id);
    List<ReservaDTO> findByClienteId(Long clienteId);
    List<ReservaDTO> findByProveedorId(Long proveedorId);
    ReservaDTO confirmarReserva(Long id);
    ReservaDTO cancelarReserva(Long id);
}
