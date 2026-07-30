package com.vdgarcia.repairShop_service.integration;

import com.vdgarcia.repairShop_service.model.dto.AgregarServicioDTO;
import com.vdgarcia.repairShop_service.model.dto.OrdenServicioDTO;
import com.vdgarcia.repairShop_service.model.enums.Estado;
import com.vdgarcia.repairShop_service.service.intef.OrdenServicioService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class OrdenServiceTest {

    @Autowired
    private OrdenServicioService service;

    @Test
    @Transactional
    void testObtenerPorId(){
        OrdenServicioDTO dto = this.service.obtenerPorId(1L);
        assertNotNull(dto);
        assertEquals(Estado.RECIBIDA,dto.getEstado());
        assertEquals(BigDecimal.valueOf(5000.00),dto.getSubtotal().setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    @Transactional
    void testObtenerPorId_NotFound(){
        RuntimeException exception = assertThrows(RuntimeException.class,()->this.service.obtenerPorId(5L));
        assertEquals("Orden no encontrada", exception.getMessage());
    }

    @Test
    @Transactional
    void testObtenerTodos(){
        List<OrdenServicioDTO> ordenes = this.service.obtenerTodos();
        assertNotNull(ordenes);
        assertEquals(2,ordenes.size());
    }

    @Test
    @Transactional
    void testCrear(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("30-07-26",formato);
        OrdenServicioDTO dto = new OrdenServicioDTO(null,fechaIngreso,null,null,null,null,null,3L,new ArrayList<>());
        OrdenServicioDTO creado = this.service.crear(dto);
        assertNotNull(creado);
        assertTrue(creado.getId()>0);
        assertEquals(Estado.RECIBIDA,creado.getEstado());
        assertEquals(BigDecimal.valueOf(0.00), creado.getSubtotal().setScale(1,RoundingMode.HALF_UP));
    }

    @Test
    @Transactional
    void testCrear_FirstException(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("30-07-26",formato);
        OrdenServicioDTO dto = new OrdenServicioDTO(null,fechaIngreso,null,Estado.RECIBIDA,null,null,null,1L,new ArrayList<>());
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.service.crear(dto));
        assertEquals("El vehículo cuente actualmente con una orden activa en estado: " + dto.getEstado(), exception.getMessage());
    }


    @Test
    @Transactional
    void testAgregarServicio(){
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,2);
        OrdenServicioDTO dto = this.service.agregarServicio(1L,agregarServicioDTO);
        assertNotNull(dto);
        assertTrue(dto.getSubtotal().compareTo(BigDecimal.valueOf(0))>0);
    }

    @Test
    @Transactional
    void testAgregarServicio_FirstException(){

    }











}
