package com.proyecto.domain.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.proyecto.domain.entities.Pago;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long id;
    private Long reservaId;
    private BigDecimal monto;
    private Pago.MetodoPago metodoPago;
    private Pago.Estado estado;
    private String referenciaPago;
}
