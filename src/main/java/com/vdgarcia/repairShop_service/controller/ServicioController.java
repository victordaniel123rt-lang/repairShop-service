package com.vdgarcia.repairShop_service.controller;

import com.vdgarcia.repairShop_service.model.dto.ServicioDTO;
import com.vdgarcia.repairShop_service.service.intef.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/servicio")
@RequiredArgsConstructor
public class ServicioController {

    private final ServicioService service;

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerPorID(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> crear(@RequestBody ServicioDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

}
