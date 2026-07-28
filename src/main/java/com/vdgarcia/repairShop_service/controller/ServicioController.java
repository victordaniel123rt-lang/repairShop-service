package com.vdgarcia.repairShop_service.controller;

import com.vdgarcia.repairShop_service.model.dto.ServicioDTO;
import com.vdgarcia.repairShop_service.service.intef.ServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/servicio")
@RequiredArgsConstructor
@Tag(name = "Servicios", description = "Controlador para gestion y creación de nuevos clientes")
public class ServicioController {

    private final ServicioService service;


    @GetMapping
    @Operation(summary = "Listar todos los servicios", description = "Muestra todos los servicios que existen hasta el momento de la consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista desplegada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<ServicioDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encuentra a un servicio en especifico", description = "Muestra a un elemento en particular con identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado y mostrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ServicioDTO> obtenerPorID(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo servicio", description = "Hace el registro de un nuevo servicio. No se debe enviar el Id en el cuerpo del JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ServicioDTO> crear(@RequestBody ServicioDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

}
