package com.vdgarcia.repairShop_service.controller;

import com.vdgarcia.repairShop_service.model.dto.VehiculoDTO;
import com.vdgarcia.repairShop_service.service.intef.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
@RequiredArgsConstructor
@Tag(name = "Vehículos", description = "Controlador para gestion y creación de nuevos vehículos")
public class VehiculoController {
    private final VehiculoService service;


    @GetMapping
    @Operation(summary = "Listar todos los vehículos", description = "Muestra todos los vehículos que existen hasta el momento de la consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista desplegada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<VehiculoDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Encuentra los vehículos de un cliente en especifico", description = "Muestra a un elemento en particular con identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado y mostrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<VehiculoDTO>> obtenerPorCliente(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorCliente(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encuentra a un vehiculo en especifico", description = "Muestra a un elemento en particular con identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado y mostrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<VehiculoDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo vehiculo", description = "Hace el registro de un nuevo vehiculo. No se debe enviar el Id en el cuerpo del JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<VehiculoDTO> crear(@RequestBody VehiculoDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }



}
