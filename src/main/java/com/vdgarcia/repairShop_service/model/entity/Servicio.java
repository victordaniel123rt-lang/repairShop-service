package com.vdgarcia.repairShop_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "servicio")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private BigDecimal precioBase;
    private Integer duracionHoras;
    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
    private List<DetalleOrden> detalles;
}
