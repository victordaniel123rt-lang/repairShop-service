package com.vdgarcia.repairShop_service.integration;

import com.vdgarcia.repairShop_service.model.dto.ClienteDTO;
import com.vdgarcia.repairShop_service.service.intef.ClienteService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {


    @Autowired
    private ClienteService service;

    @Test
    @Transactional
    void testBuscarPorId(){
        ClienteDTO dto = service.obtenerPorId(1L);
        assertNotNull(dto);
        assertEquals("Carlos", dto.getNombre());

    }

    @Test
    @Transactional
    void testBuscarPorId_NotFound(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.service.obtenerPorId(6L));
        assertEquals("Cliente no encontrado", exception.getMessage());
    }


    @Test
    @Transactional
    void testObtenerTodos(){
        List<ClienteDTO> clientes = this.service.obtenerTodos();
        assertNotNull(clientes);
        assertEquals(3,clientes.size());
    }

    @Test
    @Transactional
    void testCrear(){
        ClienteDTO dto = new ClienteDTO(null, "Agustin", "Iturbida", "7226435217", "agit@wxample.com", new ArrayList<>());
        ClienteDTO clienteDTO = this.service.crear(dto);
        assertNotNull(clienteDTO);
        assertEquals("Agustin", clienteDTO.getNombre());
        assertTrue(clienteDTO.getId()>0);
    }

    @Test
    @Transactional
    void tesActualizar(){
        ClienteDTO dto = new ClienteDTO(1L, "Carlos Javier", "Ramirez", "5544455566", "carlos.ramirez@empresa.com", new ArrayList<>());
        ClienteDTO actualizado = this.service.actualizar(1L,dto);
        assertNotNull(actualizado);
        assertEquals("Carlos Javier", actualizado.getNombre());
        assertEquals("Ramirez", actualizado.getApellido());
    }








}
