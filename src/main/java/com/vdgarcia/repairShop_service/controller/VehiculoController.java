package com.vdgarcia.repairShop_service.controller;

import com.vdgarcia.repairShop_service.model.dto.VehiculoDTO;
import com.vdgarcia.repairShop_service.service.intef.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
@RequiredArgsConstructor
public class VehiculoController {
    private final VehiculoService service;


    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<VehiculoDTO>> obtenerPorCliente(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorCliente(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> crear(@RequestBody VehiculoDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }



}
