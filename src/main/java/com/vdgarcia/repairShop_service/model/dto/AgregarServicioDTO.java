package com.vdgarcia.repairShop_service.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Schema(description = "Modelo que representa la implementación de un servicio a una orden anteriormente creada")
public class AgregarServicioDTO {
    @Schema(description = "Identificador (Id) del servicio que se va a agregar a la orden", example = "Id= 1 -> Cambio de aceite", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idServicio;
    @Schema(description = "Numero de veces que se le aplicara el servicio", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer cantidad;
}
