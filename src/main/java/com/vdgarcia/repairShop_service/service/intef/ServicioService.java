package com.vdgarcia.repairShop_service.service.intef;

import com.vdgarcia.repairShop_service.model.dto.ServicioDTO;

import java.util.List;

public interface ServicioService {

    List<ServicioDTO> obtenerTodos();
    ServicioDTO obtenerPorId(Long id);
    ServicioDTO crear(ServicioDTO dto);
}
