package com.vdgarcia.repairShop_service.unitarias;


import com.vdgarcia.repairShop_service.model.dto.OrdenServicioDTO;
import com.vdgarcia.repairShop_service.model.dto.VehiculoDTO;
import com.vdgarcia.repairShop_service.model.entity.Cliente;
import com.vdgarcia.repairShop_service.model.entity.OrderServicio;
import com.vdgarcia.repairShop_service.model.entity.Vehiculo;
import com.vdgarcia.repairShop_service.repository.ClienteRepository;
import com.vdgarcia.repairShop_service.repository.VehiculoRepository;
import com.vdgarcia.repairShop_service.service.impl.ClienteServiceImpl;
import com.vdgarcia.repairShop_service.service.impl.VehiculoServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {
    @Mock
    private VehiculoRepository repository;
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private VehiculoServiceImpl service;
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void testBuscarPorId_found(){
        List<OrderServicio> lista = new ArrayList<>();
        Cliente cliente = new Cliente();
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,cliente,lista);
        when(this.repository.findById(1L)).thenReturn(Optional.of(vehiculo));
        VehiculoDTO dto = this.service.obtenerPorId(1L);
        assertNotNull(dto);
        assertTrue(dto.getId()>0);
        assertEquals("ABC-123-A", dto.getPlaca());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void testObtenerPorId_NotFound(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.service.obtenerPorId(1L));
        assertEquals("Vehículo no encontrado", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testObtenerTodos(){
        List<OrderServicio> ordenes = new ArrayList<>();
        List<Vehiculo> lista = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Victor","Garcia", "7226435218", "vd@example.com", lista);
        lista.add(new Vehiculo(1L,"ABC-123-A",2005,1658L,cliente,ordenes));
        lista.add(new Vehiculo(2L,"ABC-123-B",2008,1658L,cliente,ordenes));
        when(this.repository.findAll()).thenReturn(lista);
        List<VehiculoDTO> dtos = this.service.obtenerTodos();
        assertNotNull(dtos);
        assertEquals(2,dtos.size());
        verify(this.repository,times(1)).findAll();
    }

    @Test
    void testCrear(){
        List<OrderServicio> lista = new ArrayList<>();
        List<OrdenServicioDTO> listadto = new ArrayList<>();
        Cliente cliente = new Cliente();
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,cliente,lista);
        VehiculoDTO vehiculoDTO = new VehiculoDTO(null,"ABC-123-A",2005,1658L,cliente.getId(),listadto);
        when(this.repository.save(any(Vehiculo.class))).thenReturn(vehiculo);
        VehiculoDTO dto = this.service.crear(vehiculoDTO);
        assertNotNull(dto);
        assertTrue(dto.getId()>0);
        assertEquals("ABC-123-A",dto.getPlaca());
        verify(this.repository,times(1)).save(any(Vehiculo.class));
    }


    @Test
    void testObtenerPorCliente_Found(){
        List<OrderServicio> ordenes = new ArrayList<>();
        List<Vehiculo> lista = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Victor","Garcia", "7226435218", "vd@example.com", lista);
        lista.add(new Vehiculo(1L,"ABC-123-A",2005,1658L,cliente,ordenes));
        lista.add(new Vehiculo(2L,"ABC-123-B",2008,1658L,cliente,ordenes));
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        List<VehiculoDTO> dtos = this.service.obtenerPorCliente(1L);
        assertNotNull(dtos);
        assertEquals(2,dtos.size());
        verify(this.clienteRepository,times(1)).findById(1L);
    }


    @Test
    void testObtenerPorCliente_NotFound(){
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->this.service.obtenerPorCliente(1L));
        assertEquals("Cliente no encontrado", exception.getMessage());
        verify(this.clienteRepository,times(1)).findById(1L);
    }










}
