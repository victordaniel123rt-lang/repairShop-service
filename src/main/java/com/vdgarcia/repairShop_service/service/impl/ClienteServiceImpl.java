package com.vdgarcia.repairShop_service.service.impl;

import com.vdgarcia.repairShop_service.mapper.Mapper;
import com.vdgarcia.repairShop_service.model.dto.ClienteDTO;
import com.vdgarcia.repairShop_service.model.entity.Cliente;
import com.vdgarcia.repairShop_service.repository.ClienteRepository;
import com.vdgarcia.repairShop_service.service.intef.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public List<ClienteDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toClienteDTO).toList();
    }

    @Override
    public ClienteDTO obtenerPorId(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        return Mapper.toClienteDTO(cliente);
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        Cliente cliente = Mapper.toCliente(dto);
        Cliente guardado = repository.save(cliente);
        return Mapper.toClienteDTO(guardado);
    }

    @Override
    public ClienteDTO actualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        Mapper.updateCliente(dto,cliente);
        Cliente actualizado = repository.save(cliente);
        return Mapper.toClienteDTO(actualizado);
    }

    @Override
    public ClienteDTO eliminar(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        repository.delete(cliente);
        return Mapper.toClienteDTO(cliente);
    }
}
