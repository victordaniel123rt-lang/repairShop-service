package com.vdgarcia.repairShop_service.integration;

import com.vdgarcia.repairShop_service.model.dto.VehiculoDTO;
import com.vdgarcia.repairShop_service.service.intef.VehiculoService;
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
public class VehiculoServiceTest {

    @Autowired
    private VehiculoService service;

    @Test
    @Transactional
    void testBuscarPorId(){
        VehiculoDTO vehiculo = this.service.obtenerPorId(1L);
        assertNotNull(vehiculo);
        assertEquals("ABC-123-A", vehiculo.getPlaca());
        assertTrue(vehiculo.getId()>0);
    }

    @Test
    @Transactional
    void testObtenerPorId_NotFound(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.service.obtenerPorId(9L));
        assertEquals("Vehículo no encontrado", exception.getMessage());
    }


    @Test
    @Transactional
    void testObtenerTodos(){
        List<VehiculoDTO> vehiculos = this.service.obtenerTodos();
        assertNotNull(vehiculos);
        assertEquals(4,vehiculos.size());
    }

    @Test
    @Transactional
    void testObtenerPorCliente(){
        List<VehiculoDTO> lista = this.service.obtenerPorCliente(1L);
        assertNotNull(lista);
        assertEquals(2,lista.size());
    }

    @Test
    @Transactional
    void testObtenerPorCliente_NotFound(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()->this.service.obtenerPorId(5L));
        assertEquals("Vehículo no encontrado", exception.getMessage());
    }



    @Test
    @Transactional
    void testCrear(){
        VehiculoDTO dto = new VehiculoDTO(null,"AER-POI-56", 2015,1698L,1L,new ArrayList<>());
        VehiculoDTO creado = this.service.crear(dto);
        assertNotNull(creado);
        assertTrue(creado.getId()>0);
        assertEquals("AER-POI-56", creado.getPlaca());

    }





}
