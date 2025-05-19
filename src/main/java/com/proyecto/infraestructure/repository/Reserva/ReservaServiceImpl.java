package com.proyecto.infraestructure.repository.Reserva;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.ReservaService;
import com.proyecto.domain.DTOs.ReservaDTO;
import com.proyecto.domain.entities.Herramienta;
import com.proyecto.domain.entities.Reserva;
import com.proyecto.domain.entities.Usuario;
import com.proyecto.infraestructure.repository.Herramienta.HerramientaRepository;
import com.proyecto.infraestructure.repository.Usuarios.UsuarioRepository;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HerramientaRepository herramientaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository,
            UsuarioRepository usuarioRepository,
            HerramientaRepository herramientaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDTO findById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + id));
        return convertToDTO(reserva);
    }

    @Override
    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva reserva = convertToEntity(reservaDTO);

        // Calcular total de pago
        long dias = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
        if (dias <= 0)
            dias = 1;

        reserva.setTotalPago(reserva.getHerramienta().getPrecioPorDia()
                .multiply(java.math.BigDecimal.valueOf(dias)));

        return convertToDTO(reservaRepository.save(reserva));
    }

    @Override
    public ReservaDTO update(Long id, ReservaDTO reservaDTO) {
        Reserva existingReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + id));

        Reserva reserva = convertToEntity(reservaDTO);
        reserva.setId(id);

        return convertToDTO(reservaRepository.save(reserva));
    }

    @Override
    public void delete(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva no encontrada con ID: " + id);
        }
        reservaRepository.deleteById(id);
    }

    @Override
    public List<ReservaDTO> findByClienteId(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> findByProveedorId(Long proveedorId) {
        return reservaRepository.findByHerramientaProveedorId(proveedorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDTO confirmarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));

        reserva.setEstado(Reserva.Estado.CONFIRMADA);
        return convertToDTO(reservaRepository.save(reserva));
    }

    @Override
    public ReservaDTO cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));

        reserva.setEstado(Reserva.Estado.CANCELADA);
        return convertToDTO(reservaRepository.save(reserva));
    }

    private ReservaDTO convertToDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        BeanUtils.copyProperties(reserva, dto);

        if (reserva.getCliente() != null) {
            dto.setClienteId(reserva.getCliente().getId());
            dto.setClienteNombre(reserva.getCliente().getNombre());
        }

        if (reserva.getHerramienta() != null) {
            dto.setHerramientaId(reserva.getHerramienta().getId());
            dto.setHerramientaNombre(reserva.getHerramienta().getNombre());
            dto.setHerramientaImagen(reserva.getHerramienta().getImagenUrl());
        }

        return dto;
    }

    private Reserva convertToEntity(ReservaDTO dto) {
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(dto, reserva);
        
        if (dto.getClienteId() != null) {
            Usuario cliente = usuarioRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
            reserva.setCliente(cliente);
        }
        
        if (dto.getHerramientaId() != null) {
            Herramienta herramienta = herramientaRepository.findById(dto.getHerramientaId())
                    .orElseThrow(() -> new EntityNotFoundException("Herramienta no encontrada"));
            reserva.setHerramienta(herramienta);
        }
        
        return reserva;
    }

}
