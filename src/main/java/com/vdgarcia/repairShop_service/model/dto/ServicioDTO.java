package com.vdgarcia.repairShop_service.model.dto;

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
public class ServicioDTO {
    private Long id;
    @NotBlank(message = "El nombre del servicio es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    @NotNull(message = "El precio base es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio base debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio no puede exceder 8 dígitos enteros y 2 decimales")
    private BigDecimal precioBase;
    @NotNull(message = "La duración en horas es obligatoria")
    @Min(value = 1, message = "La duración mínima es de 1 hora")
    @Max(value = 100, message = "La duración no puede ser excesiva (máximo 100 horas)")
    private Integer duracionHoras;
    private List<DetalleOrdenDTO> detalles;
}
