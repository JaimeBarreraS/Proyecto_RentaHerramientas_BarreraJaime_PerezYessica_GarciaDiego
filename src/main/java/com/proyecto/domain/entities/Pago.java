package com.proyecto.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
    
    private BigDecimal monto;
    
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;
    
    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;
    
    @Column(name = "referencia_pago")
    private String referenciaPago;
    
    public enum MetodoPago {
        TARJETA_CREDITO, TARJETA_DEBITO, TRANSFERENCIA, EFECTIVO
    }
    
    public enum Estado {
        PENDIENTE, PROCESANDO, COMPLETADO, FALLIDO, REEMBOLSADO
    }
}
