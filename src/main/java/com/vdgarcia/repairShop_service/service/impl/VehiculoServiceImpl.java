package com.vdgarcia.repairShop_service.service.impl;

import com.vdgarcia.repairShop_service.mapper.Mapper;
import com.vdgarcia.repairShop_service.model.dto.VehiculoDTO;
import com.vdgarcia.repairShop_service.model.entity.Cliente;
import com.vdgarcia.repairShop_service.model.entity.Vehiculo;
import com.vdgarcia.repairShop_service.repository.ClienteRepository;
import com.vdgarcia.repairShop_service.repository.VehiculoRepository;
import com.vdgarcia.repairShop_service.service.intef.ClienteService;
import com.vdgarcia.repairShop_service.service.intef.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ClienteService service;

    @Override
    public List<VehiculoDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::tovehiculoDTO).toList();
    }

    @Override
    public VehiculoDTO obtenerPorId(Long id) {
        Vehiculo vehiculo = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Vehículo no encontrado")
        );
        return Mapper.tovehiculoDTO(vehiculo);
    }

    @Override
    public List<VehiculoDTO> obtenerPorCliente(Long id) {
        Cliente cliente = clienteRepository.findById(1L).orElseThrow(
                ()->new IllegalArgumentException("Cliente no encontrado")
        );
        return cliente.getVehiculos().stream().map(Mapper::tovehiculoDTO).toList();
    }

    @Override
    public VehiculoDTO crear(VehiculoDTO dto) {
        Vehiculo vehiculo = Mapper.tovehiculo(dto);
        Vehiculo guardado = repository.save(vehiculo);
        return Mapper.tovehiculoDTO(guardado);
    }
}
