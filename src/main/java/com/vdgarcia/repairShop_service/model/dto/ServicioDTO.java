package com.vdgarcia.repairShop_service.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Modelo que representa la información de un servicio ofrecido por el taller")
public class ServicioDTO {
    @Schema(description = "ID único del servicio (autogenerado)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotBlank(message = "El nombre del servicio es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del servicio", example = "Cambio de aceite", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    @NotNull(message = "El precio base es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio base debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio no puede exceder 8 dígitos enteros y 2 decimales")
    @Schema(description = "Precio base por una implementación del servicio", example = "900", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal precioBase;
    @NotNull(message = "La duración en horas es obligatoria")
    @Min(value = 1, message = "La duración mínima es de 1 hora")
    @Max(value = 100, message = "La duración no puede ser excesiva (máximo 100 horas)")
    @Schema(description = "Duración aproximada en que se aplica el servicio", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer duracionHoras;
    private List<DetalleOrdenDTO> detalles;
}
