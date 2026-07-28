package com.vdgarcia.repairShop_service.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class VehiculoDTO {
        private Long id;
        private String placa;
        private Integer anio;
        private Long kilometraje;
        private Long cliente;
        private List<OrdenServicioDTO> ordenes;
    }
