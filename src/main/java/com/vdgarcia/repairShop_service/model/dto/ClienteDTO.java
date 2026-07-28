package com.vdgarcia.repairShop_service.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "Modelo que representa la información de un cliente")
public class ClienteDTO {
    @Schema(description = "ID único del cliente (autogenerado)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Schema(description = "Nombre con el que se identifica el cliente", example = "Víctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Schema(description = "Apellido con el que se identifica el cliente", example = "García", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El teléfono debe contener entre 7 y 15 dígitos y opcionalmente el prefijo '+'")
    @Schema(description = "Numero telefonico del cliente", example = "555655145")
    private String telefono;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    @Schema(description = "email personal del cliente", example = "vgarcia@example.com")
    private String correo;
    private List<VehiculoDTO> vehiculos;
}
