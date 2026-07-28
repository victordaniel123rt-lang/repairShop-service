package com.vdgarcia.repairShop_service.service.intef;

import com.vdgarcia.repairShop_service.model.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> obtenerTodos();
    ClienteDTO obtenerPorId(Long id);
    ClienteDTO crear(ClienteDTO dto);
    ClienteDTO actualizar(Long id, ClienteDTO dto);
    ClienteDTO eliminar(Long id);

}
