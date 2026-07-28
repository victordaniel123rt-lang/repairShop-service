package com.vdgarcia.repairShop_service.controller;


import com.vdgarcia.repairShop_service.model.dto.AgregarServicioDTO;
import com.vdgarcia.repairShop_service.model.dto.CambiarEstadoDTO;
import com.vdgarcia.repairShop_service.model.dto.OrdenServicioDTO;
import com.vdgarcia.repairShop_service.service.intef.OrdenServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
@RequiredArgsConstructor
@Tag(name = "Ordenes", description = "Controlador para gestion y creación de ordenes, implementación de servicios, cambios de estado")
public class OrdenController {

    private final OrdenServicioService service;


    @GetMapping
    @Operation(summary = "Listar todos las ordenes", description = "Muestra todas las ordenes que existen hasta el momento de la consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista desplegada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<OrdenServicioDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Encuentra a una orden en especifico", description = "Muestra a un elemento en particular con identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado y mostrado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<OrdenServicioDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea una nueva orden", description = "Hace el registro de una nueva orden. No se debe enviar el Id en el cuerpo del JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<OrdenServicioDTO> crear(@RequestBody OrdenServicioDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}/agregar")
    @Operation(summary = "Agrega un servicio a la orden antes creada", description = "Hace el registro de un nuevo servicio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio agregado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<OrdenServicioDTO> agregarServicio(@PathVariable Long id, @RequestBody AgregarServicioDTO dto){
        return ResponseEntity.ok(service.agregarServicio(id,dto));
    }


    @PutMapping("/{id}cambiar")
    @Operation(summary = "Cambia el estado de una orden", description = "Cambia el estado de una orden.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estado actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<OrdenServicioDTO> cambiarEstado(@PathVariable Long id, @RequestBody CambiarEstadoDTO dto){
        return ResponseEntity.ok(service.cambiarEstado(id,dto));
    }





}
