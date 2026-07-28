package com.vdgarcia.repairShop_service.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Modelo que representa la información de un vehiculo")
public class VehiculoDTO {
        @Schema(description = "ID único del vehiculo (autogenerado)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
        private Long id;
        @NotBlank(message = "La placa es obligatoria")
        @Pattern(regexp = "^[A-Za-z0-9-]{6,10}$", message = "La placa debe ser alfanumérica y tener entre 6 y 10 caracteres")
        @Schema(description = "Placa con la que se identifica al vehiculo", example = "ABC-123-A", requiredMode = Schema.RequiredMode.REQUIRED)
        private String placa;
         @NotNull(message = "El año es obligatorio")
        @Min(value = 1900, message = "El año no puede ser menor a 1900")
        @Max(value = 2027, message = "El año no puede ser superior al año actual/siguiente")
         @Schema(description = "Año del vehiculo", example = "2006", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer anio;
        @NotNull(message = "El kilometraje es obligatorio")
        @Min(value = 0, message = "El kilometraje no puede ser negativo")
        @Schema(description = "Kilómetros recorridos por el vehiculo", example = "1250", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long kilometraje;
        @NotNull(message = "El ID del cliente es obligatorio")
        @Schema(description = "Id del propietario de la unidad", example = "ID=1 -> Victor Garcia", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long cliente;
        private List<OrdenServicioDTO> ordenes;
    }
