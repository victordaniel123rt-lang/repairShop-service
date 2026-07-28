package com.vdgarcia.repairShop_service.model.dto;

import com.vdgarcia.repairShop_service.model.entity.Estado;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenServicioDTO {
    private Long id;
    private LocalDate fechaIngreso;
    private LocalDate fechaEntrega;
    private Estado estado;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    private Long vehiculo;
    private List<DetalleOrdenDTO> detalles;
}
