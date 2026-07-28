package com.vdgarcia.repairShop_service.service.intef;

import com.vdgarcia.repairShop_service.model.dto.VehiculoDTO;

import java.util.List;

public interface VehiculoService {

    List<VehiculoDTO> obtenerTodos();
    VehiculoDTO obtenerPorId(Long id);
    List<VehiculoDTO> obtenerPorCliente(Long id);
    VehiculoDTO crear(VehiculoDTO dto);
}
