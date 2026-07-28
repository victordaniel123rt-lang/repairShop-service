package com.vdgarcia.repairShop_service.model.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicioDTO {
    private Long id;
    private String nombre;
    private BigDecimal precioBase;
    private Integer duracionHoras;
    private List<DetalleOrdenDTO> detalles;
}
