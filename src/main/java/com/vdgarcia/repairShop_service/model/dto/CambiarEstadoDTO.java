package com.vdgarcia.repairShop_service.model.dto;

import com.vdgarcia.repairShop_service.model.enums.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Schema(description = "Modelo que representa el cambio de estado en que se encuentra una orden")
public class CambiarEstadoDTO {
    @Schema(description = "Representa el estado al que se va a cambiar la orden", example = "RECIBIDA", requiredMode = Schema.RequiredMode.REQUIRED)
    private Estado nuevoEstado;
}
