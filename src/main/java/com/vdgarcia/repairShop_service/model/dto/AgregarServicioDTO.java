package com.vdgarcia.repairShop_service.model.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AgregarServicioDTO {
    private Long idServicio;
    private Integer cantidad;
}
