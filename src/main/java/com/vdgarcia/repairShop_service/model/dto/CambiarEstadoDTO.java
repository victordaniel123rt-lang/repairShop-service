package com.vdgarcia.repairShop_service.model.dto;

import com.vdgarcia.repairShop_service.model.entity.Estado;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CambiarEstadoDTO {
    private Estado nuevoEstado;
}
