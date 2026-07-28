package com.vdgarcia.repairShop_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden_servicio")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderServicio {

    private Long id;
    private LocalDate fechaIngreso;
    private LocalDate fechaEntrega;
    private Estado estado;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    @OneToMany(mappedBy = "ordenServicio", fetch = FetchType.LAZY)
    private List<DetalleOrden> detalles;

}
