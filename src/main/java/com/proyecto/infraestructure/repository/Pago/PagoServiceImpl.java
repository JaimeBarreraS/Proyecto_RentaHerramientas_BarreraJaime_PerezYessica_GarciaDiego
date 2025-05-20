package com.proyecto.infraestructure.repository.Pago;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.PagoService;
import com.proyecto.domain.DTOs.PagoDTO;
import com.proyecto.domain.entities.Pago;
import com.proyecto.domain.entities.Reserva;
import com.proyecto.infraestructure.repository.Reserva.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;

    @Autowired
    public PagoServiceImpl(PagoRepository pagoRepository, ReservaRepository reservaRepository) {
        this.pagoRepository = pagoRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<PagoDTO> findAll() {
        return pagoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PagoDTO findById(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + id));
        return convertToDTO(pago);
    }

    @Override
    public PagoDTO save(PagoDTO pagoDTO) {
        Pago pago = convertToEntity(pagoDTO);
        return convertToDTO(pagoRepository.save(pago));
    }

    @Override
    public PagoDTO update(Long id, PagoDTO pagoDTO) {
        Pago existingPago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + id));
        
        Pago pago = convertToEntity(pagoDTO);
        pago.setId(id);
        
        return convertToDTO(pagoRepository.save(pago));
    }

    @Override
    public void delete(Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pago no encontrado con ID: " + id);
        }
        pagoRepository.deleteById(id);
    }

    @Override
    public PagoDTO findByReservaId(Long reservaId) {
        Pago pago = pagoRepository.findByReservaId(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado para reserva: " + reservaId));
        return convertToDTO(pago);
    }

    @Override
    public PagoDTO procesarPago(Long reservaId, PagoDTO pagoDTO) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
        
        Pago pago = new Pago();
        pago.setReserva(reserva);
        pago.setMonto(reserva.getTotalPago());
        pago.setMetodoPago(pagoDTO.getMetodoPago());
        pago.setEstado(Pago.Estado.COMPLETADO);
        pago.setFechaPago(LocalDateTime.now());
        pago.setReferenciaPago(UUID.randomUUID().toString());
        
        // Actualizar estado de reserva
        reserva.setEstado(Reserva.Estado.CONFIRMADA);
        reservaRepository.save(reserva);
        
        return convertToDTO(pagoRepository.save(pago));
    }

    private PagoDTO convertToDTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        BeanUtils.copyProperties(pago, dto);
        
        if (pago.getReserva() != null) {
            dto.setReservaId(pago.getReserva().getId());
        }
        
        return dto;
    }

    private Pago convertToEntity(PagoDTO dto) {
        Pago pago = new Pago();
        BeanUtils.copyProperties(dto, pago);
        
        if (dto.getReservaId() != null) {
            Reserva reserva = reservaRepository.findById(dto.getReservaId())
                    .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            pago.setReserva(reserva);
        }
        
        return pago;
    }
}
