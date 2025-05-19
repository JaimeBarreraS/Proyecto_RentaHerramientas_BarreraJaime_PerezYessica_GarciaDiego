package com.proyecto.infraestructure.repository.Herramienta;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.application.service.HerramientaService;
import com.proyecto.domain.DTOs.HerramientaDTO;
import com.proyecto.domain.entities.Herramienta;
import com.proyecto.domain.entities.Usuario;
import com.proyecto.infraestructure.repository.Usuarios.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HerramientaServiceImpl implements HerramientaService {

    private final HerramientaRepository herramientaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public HerramientaServiceImpl(HerramientaRepository herramientaRepository, 
        UsuarioRepository usuarioRepository) {
        this.herramientaRepository = herramientaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<HerramientaDTO> findAll() {
        return herramientaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HerramientaDTO findById(Long id) {
        Herramienta herramienta = herramientaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Herramienta no encontrada con ID: " + id));
        return convertToDTO(herramienta);
    }

    @Override
    public HerramientaDTO save(HerramientaDTO herramientaDTO) {
        Herramienta herramienta = convertToEntity(herramientaDTO);
        return convertToDTO(herramientaRepository.save(herramienta));
    }

    @Override
    public HerramientaDTO update(Long id, HerramientaDTO herramientaDTO) {
        Herramienta existingHerramienta = herramientaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Herramienta no encontrada con ID: " + id));
        
        Herramienta herramienta = convertToEntity(herramientaDTO);
        herramienta.setId(id);
        
        return convertToDTO(herramientaRepository.save(herramienta));
    }

    @Override
    public void delete(Long id) {
        if (!herramientaRepository.existsById(id)) {
            throw new EntityNotFoundException("Herramienta no encontrada con ID: " + id);
        }
        herramientaRepository.deleteById(id);
    }

    @Override
    public List<HerramientaDTO> findByCategoria(String categoria) {
        return herramientaRepository.findByCategoria(categoria).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HerramientaDTO> findByProveedorId(Long proveedorId) {
        return herramientaRepository.findByProveedorId(proveedorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HerramientaDTO> findHerramientasDisponibles(LocalDate fechaInicio, LocalDate fechaFin) {
        return herramientaRepository.findHerramientasDisponibles(fechaInicio, fechaFin).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findCategorias() {
        return herramientaRepository.findAll().stream()
                .map(Herramienta::getCategoria)
                .distinct()
                .collect(Collectors.toList());
    }

    private HerramientaDTO convertToDTO(Herramienta herramienta) {
        HerramientaDTO dto = new HerramientaDTO();
        BeanUtils.copyProperties(herramienta, dto);
        
        if (herramienta.getProveedor() != null) {
            dto.setProveedorId(herramienta.getProveedor().getId());
            dto.setProveedorNombre(herramienta.getProveedor().getNombre());
        }
        
        return dto;
    }

    private Herramienta convertToEntity(HerramientaDTO dto) {
        Herramienta herramienta = new Herramienta();
        BeanUtils.copyProperties(dto, herramienta);
        
        if (dto.getProveedorId() != null) {
            Usuario proveedor = usuarioRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));
            herramienta.setProveedor(proveedor);
        }
        
        return herramienta;
    }
}
