package com.vdgarcia.repairShop_service.integration;


import com.vdgarcia.repairShop_service.model.dto.ClienteDTO;
import com.vdgarcia.repairShop_service.model.dto.VehiculoDTO;
import com.vdgarcia.repairShop_service.model.entity.Cliente;
import com.vdgarcia.repairShop_service.model.entity.Vehiculo;
import com.vdgarcia.repairShop_service.repository.ClienteRepository;
import com.vdgarcia.repairShop_service.service.impl.ClienteServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;
    @InjectMocks
    private ClienteServiceImpl service;

@Test
void testBuscarPorId_Found(){
    List<Vehiculo> lista = new ArrayList<>();
    Cliente cliente = new Cliente(1L,"Victor","Garcia", "7226435218", "vd@example.com", lista);
    when(this.repository.findById(1L)).thenReturn(Optional.of(cliente));
    ClienteDTO dto = this.service.obtenerPorId(1L);
    assertNotNull(dto);
    assertEquals("Victor", dto.getNombre());
    verify(this.repository,times(1)).findById(1L);
}

    @Test
    void testBuscarPorId_NotFound(){
    when(this.repository.findById(1L)).thenReturn(Optional.empty());
    RuntimeException exception = assertThrows(RuntimeException.class, ()->this.service.obtenerPorId(1L));
    assertEquals("Cliente no encontrado",exception.getMessage());
    verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testBuscarTodos(){
        List<Vehiculo> lista = new ArrayList<>();
    List<Cliente> clientes = List.of(
             new Cliente(1L,"Victor","Garcia", "7226435218", "vd@example.com", lista),
             new Cliente(1L,"Daniel","Garcia", "7226435218", "vg@example.com", lista)
    );
    when(this.repository.findAll()).thenReturn(clientes);
    List<ClienteDTO> dtos = this.service.obtenerTodos();
    assertNotNull(dtos);
    assertEquals(2,dtos.size());
    verify(this.repository,times(1)).findAll();

    }

    @Test
    void testCrearCliente(){
        List<Vehiculo> lista = new ArrayList<>();
        List<VehiculoDTO> listaDTO = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Victor","Garcia", "7226435218", "vd@example.com", lista);
        ClienteDTO clienteDTO = new ClienteDTO(null,"Victor","Garcia", "7226435218", "vd@example.com", listaDTO);
        when(this.repository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteDTO dto = this.service.crear(clienteDTO);
        assertNotNull(dto);
        assertTrue(dto.getId()>0);
        assertEquals("Victor", dto.getNombre());
        verify(this.repository,times(1)).save(any(Cliente.class));
    }


    @Test
    void testActualizar_Found(){
        List<Vehiculo> lista = new ArrayList<>();
        List<VehiculoDTO> listaDTO = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Victor","Garcia", "7226435218", "vd@example.com", lista);
        ClienteDTO clienteDTO = new ClienteDTO(null,"Victor","Elacio", "7226435225", "vg@example.com", listaDTO);
        Cliente cliente2 = new Cliente(1L,"Victor","Elacio", "7226435225", "vg@example.com", lista);
        when(this.repository.findById(1L)).thenReturn(Optional.of(cliente));
        when(this.repository.save(any(Cliente.class))).thenReturn(cliente2);
        ClienteDTO dto = this.service.actualizar(1L, clienteDTO);
        assertNotNull(dto);
        assertEquals("Elacio",dto.getApellido());
        assertEquals("7226435225",dto.getTelefono());
        verify(this.repository,times(1)).findById(1L);
        verify(this.repository,times(1)).save(any(Cliente.class));

    }


    @Test
    void testActualizar_NotFound(){
    List<VehiculoDTO> listaDTO = new ArrayList<>();
    ClienteDTO clienteDTO = new ClienteDTO(null,"Victor","Elacio", "7226435225", "vg@example.com", listaDTO);
    when(this.repository.findById(1L)).thenReturn(Optional.empty());
    RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.service.actualizar(1L, clienteDTO));
    assertEquals("Cliente no encontrado", exception.getMessage());
    verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testEliminar_Found(){
        List<Vehiculo> lista = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Victor","Garcia", "7226435218", "vd@example.com", lista);
        when(this.repository.findById(1L)).thenReturn(Optional.of(cliente));
        ClienteDTO dto = this.service.eliminar(1L);
        assertNotNull(dto);
        verify(this.repository,times(1)).findById(1L);
        verify(this.repository,times(1)).delete(any(Cliente.class));
    }


    @Test
    void testEliminar_NotFound(){
    when(this.repository.findById(1L)).thenReturn(Optional.empty());
    RuntimeException exception = assertThrows(RuntimeException.class, ()->this.service.eliminar(1L));
    assertEquals("Cliente no encontrado", exception.getMessage());
    verify(this.repository,times(1)).findById(1L);
    }










}
