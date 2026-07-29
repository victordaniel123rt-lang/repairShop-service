package com.vdgarcia.repairShop_service.integration;

import com.vdgarcia.repairShop_service.model.dto.DetalleOrdenDTO;
import com.vdgarcia.repairShop_service.model.dto.ServicioDTO;
import com.vdgarcia.repairShop_service.model.entity.DetalleOrden;
import com.vdgarcia.repairShop_service.model.entity.Servicio;
import com.vdgarcia.repairShop_service.repository.ServicioRepository;
import com.vdgarcia.repairShop_service.service.impl.ServicioServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServicioServiceTest {

    @Mock
    private ServicioRepository repository;
    @InjectMocks
    private ServicioServiceImpl service;

    @Test
    void testObtenerPorId_Found(){
        List<DetalleOrden> detalles = new ArrayList<>();
        Servicio servicio = new Servicio(1L,"Cambio de aceite", BigDecimal.valueOf(250),2,detalles);
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        ServicioDTO dto = this.service.obtenerPorId(1L);
        assertNotNull(dto);
        assertEquals("Cambio de aceite",dto.getNombre());
        assertEquals(2,dto.getDuracionHoras());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testBuscarPorId_NotFound(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->this.service.obtenerPorId(1L));
        assertEquals("Servicio no encontrado", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void testObtenerTodos(){
        List<DetalleOrden> detalles = new ArrayList<>();
        List<Servicio> servicios = List.of(
                new Servicio(1L,"Cambio de aceite", BigDecimal.valueOf(250),2,detalles),
                new Servicio(2L,"Cambio de llanta", BigDecimal.valueOf(150),1,detalles)
        );
        when(this.repository.findAll()).thenReturn(servicios);

        List<ServicioDTO> dtos = this.service.obtenerTodos();
        assertNotNull(dtos);
        assertEquals(2,dtos.size());
        verify(this.repository,times(1)).findAll();
    }

    @Test
    void testCrear(){
        List<DetalleOrden> detalles = new ArrayList<>();
        List<DetalleOrdenDTO> detallesdto = new ArrayList<>();
        Servicio servicio = new Servicio(1L,"Cambio de aceite", BigDecimal.valueOf(250),2,detalles);
        ServicioDTO servicioDto = new ServicioDTO(null,"Cambio de aceite", BigDecimal.valueOf(250),2,detallesdto);
        when(this.repository.save(any(Servicio.class))).thenReturn(servicio);
        ServicioDTO dto = this.service.crear(servicioDto);
        assertNotNull(dto);
        assertEquals("Cambio de aceite",dto.getNombre());
        assertTrue(dto.getId()>0);
        assertEquals(2,dto.getDuracionHoras());
        verify(this.repository,times(1)).save(any(Servicio.class));
    }

}
