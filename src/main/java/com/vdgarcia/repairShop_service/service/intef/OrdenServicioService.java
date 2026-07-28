package com.vdgarcia.repairShop_service.service.intef;

import com.vdgarcia.repairShop_service.model.dto.AgregarServicioDTO;
import com.vdgarcia.repairShop_service.model.dto.CambiarEstadoDTO;
import com.vdgarcia.repairShop_service.model.dto.OrdenServicioDTO;

import java.util.List;

public interface OrdenServicioService {

    List<OrdenServicioDTO> obtenerTodos();
    OrdenServicioDTO obtenerPorId(Long id);
    OrdenServicioDTO crear(OrdenServicioDTO dto);
    OrdenServicioDTO agregarServicio(Long id, AgregarServicioDTO dto);
    OrdenServicioDTO cambiarEstado(Long id, CambiarEstadoDTO dto);
}
