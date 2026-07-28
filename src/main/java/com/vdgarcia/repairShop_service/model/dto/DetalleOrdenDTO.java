package com.vdgarcia.repairShop_service.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleOrdenDTO {
    private Long id;
    private Integer cantidad;
    private BigDecimal subtotal;
    private Long orden;
    private Long servicio;
}
