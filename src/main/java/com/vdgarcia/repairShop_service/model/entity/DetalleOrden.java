package com.vdgarcia.repairShop_service.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_orden")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DetalleOrden {

    private Long id;
    private Integer cantidad;
    private BigDecimal subtotal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordenServicio_id")
    private OrderServicio orden;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;


}
