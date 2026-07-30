package com.vdgarcia.repairShop_service.integration;


import com.vdgarcia.repairShop_service.model.dto.ServicioDTO;
import com.vdgarcia.repairShop_service.service.intef.ServicioService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ServicioServiceTest {

    @Autowired
    private ServicioService service;

    @Test
    @Transactional
    void testBuscarPorId(){
        ServicioDTO servicioDTO = this.service.obtenerPorId(1L);
        assertNotNull(servicioDTO);
        assertEquals("Cambio de aceite", servicioDTO.getNombre());
        assertEquals(BigDecimal.valueOf(2500.00),servicioDTO.getPrecioBase().setScale(1, RoundingMode.HALF_UP));
        assertEquals(1,servicioDTO.getDuracionHoras());
    }


    @Test
    @Transactional
    void testBuscarPorId_NotFound(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.service.obtenerPorId(8L));
        assertEquals("Servicio no encontrado", exception.getMessage());
    }

    @Test
    @Transactional
    void testObtenerTodos(){
        List<ServicioDTO> lista = this.service.obtenerTodos();
        assertNotNull(lista);
        assertEquals(3,lista.size());
    }

    @Test
    @Transactional
    void testCrear(){
        ServicioDTO dto = new ServicioDTO(null,"Autolavado",BigDecimal.valueOf(120),1,new ArrayList<>());
        ServicioDTO creado = this.service.crear(dto);
        assertNotNull(creado);
        assertEquals("Autolavado", creado.getNombre());
        assertEquals(1,creado.getDuracionHoras());
    }


}
