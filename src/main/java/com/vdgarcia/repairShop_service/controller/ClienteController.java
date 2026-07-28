package com.vdgarcia.repairShop_service.controller;

import com.vdgarcia.repairShop_service.model.dto.ClienteDTO;
import com.vdgarcia.repairShop_service.service.intef.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Controlador para gestion y creación de nuevos clientes")
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Muestra todos los clientes que existen hasta el momento de la consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista desplegada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<ClienteDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encuentra a un cliente en especifico", description = "Muestra a un elemento en particular con identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado y mostrado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo cliente", description = "Hace el registro de un nuevo cliente. No se debe enviar el Id en el cuerpo del JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un cliente", description = "Hace la actualización correspondiente de un cliente. Es necesario incluir el id en el cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto){
        return ResponseEntity.ok(service.actualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un cliente", description = "Elimina un cliente creado con anterioridad. Es necesario el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200/2004", description = "Elemento eliminado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ClienteDTO> eliminar (@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }


}
