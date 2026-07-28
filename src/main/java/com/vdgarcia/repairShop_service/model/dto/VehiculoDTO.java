package com.vdgarcia.repairShop_service.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class VehiculoDTO {
        private Long id;
        @NotBlank(message = "La placa es obligatoria")
        @Pattern(regexp = "^[A-Za-z0-9-]{6,10}$", message = "La placa debe ser alfanumérica y tener entre 6 y 10 caracteres")
        private String placa;
         @NotNull(message = "El año es obligatorio")
        @Min(value = 1900, message = "El año no puede ser menor a 1900")
        @Max(value = 2027, message = "El año no puede ser superior al año actual/siguiente")
        private Integer anio;
        @NotNull(message = "El kilometraje es obligatorio")
        @Min(value = 0, message = "El kilometraje no puede ser negativo")
        private Long kilometraje;
        @NotNull(message = "El ID del cliente es obligatorio")
        private Long cliente;
        private List<OrdenServicioDTO> ordenes;
    }
