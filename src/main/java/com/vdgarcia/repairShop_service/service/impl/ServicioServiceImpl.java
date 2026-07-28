package com.vdgarcia.repairShop_service.service.impl;

import com.vdgarcia.repairShop_service.mapper.Mapper;
import com.vdgarcia.repairShop_service.model.dto.ServicioDTO;
import com.vdgarcia.repairShop_service.model.entity.Servicio;
import com.vdgarcia.repairShop_service.repository.ServicioRepository;
import com.vdgarcia.repairShop_service.service.intef.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repository;

    @Override
    public List<ServicioDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toServicioDTO).toList();
    }

    @Override
    public ServicioDTO obtenerPorId(Long id) {
        return Mapper.toServicioDTO(repository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Servicio no encontrado")
        ));
    }

    @Override
    public ServicioDTO crear(ServicioDTO dto) {
        Servicio servicio =Mapper.toServicio(dto);
        Servicio creado = repository.save(servicio);
        return Mapper.toServicioDTO(creado);
    }
}
