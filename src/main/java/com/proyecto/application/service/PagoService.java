package com.proyecto.application.service;

import java.util.List;

import com.proyecto.domain.DTOs.PagoDTO;

public interface PagoService {
    List<PagoDTO> findAll();
    PagoDTO findById(Long id);
    PagoDTO save(PagoDTO pagoDTO);
    PagoDTO update(Long id, PagoDTO pagoDTO);
    void delete(Long id);
    PagoDTO findByReservaId(Long reservaId);
    PagoDTO procesarPago(Long reservaId, PagoDTO pagoDTO);
    public List<PagoDTO> findByDia(int dia);
    public List<PagoDTO> findByMes(int mes);
    public List<PagoDTO> findByAno(int ano);
}