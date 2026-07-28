package com.vdgarcia.repairShop_service.controller;


import com.vdgarcia.repairShop_service.model.dto.AgregarServicioDTO;
import com.vdgarcia.repairShop_service.model.dto.CambiarEstadoDTO;
import com.vdgarcia.repairShop_service.model.dto.OrdenServicioDTO;
import com.vdgarcia.repairShop_service.service.intef.OrdenServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenServicioService service;


    @GetMapping
    public ResponseEntity<List<OrdenServicioDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrdenServicioDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdenServicioDTO> crear(@RequestBody OrdenServicioDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}/agregar")
    public ResponseEntity<OrdenServicioDTO> agregarServicio(@PathVariable Long id, @RequestBody AgregarServicioDTO dto){
        return ResponseEntity.ok(service.agregarServicio(id,dto));
    }


    @PutMapping("/{id}cambiar")
    public ResponseEntity<OrdenServicioDTO> cambiarEstado(@PathVariable Long id, @RequestBody CambiarEstadoDTO dto){
        return ResponseEntity.ok(service.cambiarEstado(id,dto));
    }





}
