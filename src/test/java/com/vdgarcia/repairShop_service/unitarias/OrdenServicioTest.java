package com.vdgarcia.repairShop_service.unitarias;

import com.vdgarcia.repairShop_service.model.dto.AgregarServicioDTO;
import com.vdgarcia.repairShop_service.model.dto.CambiarEstadoDTO;
import com.vdgarcia.repairShop_service.model.dto.DetalleOrdenDTO;
import com.vdgarcia.repairShop_service.model.dto.OrdenServicioDTO;
import com.vdgarcia.repairShop_service.model.entity.*;
import com.vdgarcia.repairShop_service.model.enums.Estado;
import com.vdgarcia.repairShop_service.repository.DetalleOrdenRepository;
import com.vdgarcia.repairShop_service.repository.OrdenRepository;
import com.vdgarcia.repairShop_service.repository.ServicioRepository;
import com.vdgarcia.repairShop_service.service.impl.OrdenServicioImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrdenServicioTest {

    @Mock
    private OrdenRepository repository;
    @Mock
    private ServicioRepository servicioRepository;
    @Mock
    private DetalleOrdenRepository detalleOrdenRepository;
    @InjectMocks
    private OrdenServicioImpl servicio;

    @Test
    void testBuscarPordId_NotFound(){
        List<DetalleOrden> detalles = new ArrayList<>();
        Vehiculo vehiculo = new Vehiculo();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio orderServicio = new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,detalles);
        when(this.repository.findById(1L)).thenReturn(Optional.of(orderServicio));
        OrdenServicioDTO dto = this.servicio.obtenerPorId(1L);
        assertNotNull(dto);
        assertEquals(Estado.RECIBIDA,dto.getEstado());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.obtenerPorId(1L));
        assertEquals("Orden no encontrada", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testObtenerTodos(){
        List<DetalleOrden> detalles = new ArrayList<>();
        Vehiculo vehiculo = new Vehiculo();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        List<OrderServicio> lista = List.of(
                new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                        BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,detalles)
        );
        when(this.repository.findAll()).thenReturn(lista);
        List<OrdenServicioDTO> dtos = this.servicio.obtenerTodos();
        assertNotNull(dtos);
        assertEquals(1,dtos.size());
        verify(this.repository,times(1)).findAll();
    }


    @Test
    void testCrear(){
        List<DetalleOrden> detalles = new ArrayList<>();
        List<DetalleOrdenDTO> detallesdto = new ArrayList<>();
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,detalles);
        OrdenServicioDTO servicioDTO =  new OrdenServicioDTO(null,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo.getId(),detallesdto);
        when(this.repository.findFirstByVehiculoIdAndEstadoIn(vehiculo.getId(),List.of(
                Estado.RECIBIDA,
                Estado.DIAGNOSTICO,
                Estado.EN_REPARACION,
                Estado.LISTA
        ))).thenReturn(Optional.empty());
        when(this.repository.save(any(OrderServicio.class))).thenReturn(servicio);
        OrdenServicioDTO dto = this.servicio.crear(servicioDTO);
        assertNotNull(dto);
        assertTrue(dto.getId()>0);
        verify(this.repository,times(1)).save(any(OrderServicio.class));
        verify(this.repository,times(1)).findFirstByVehiculoIdAndEstadoIn(vehiculo.getId(),List.of(
                Estado.RECIBIDA,
                Estado.DIAGNOSTICO,
                Estado.EN_REPARACION,
                Estado.LISTA
        ));
    }

    @Test
    void testCrear_Exception(){
        List<DetalleOrden> detalles = new ArrayList<>();
        List<DetalleOrdenDTO> detallesdto = new ArrayList<>();
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,detalles);
        OrdenServicioDTO servicioDTO =  new OrdenServicioDTO(null,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo.getId(),detallesdto);
        DetalleOrden detalleOrden = new DetalleOrden(1L,2,BigDecimal.valueOf(150),servicio,new Servicio());
        when(this.repository.findFirstByVehiculoIdAndEstadoIn(vehiculo.getId(),List.of(
                Estado.RECIBIDA,
                Estado.DIAGNOSTICO,
                Estado.EN_REPARACION,
                Estado.LISTA
        ))).thenReturn(Optional.of(servicio));

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.crear(servicioDTO));
        assertEquals("El vehículo cuente actualmente con una orden activa en estado: " + servicio.getEstado(), exception.getMessage());
        verify(this.repository,times(1)).findFirstByVehiculoIdAndEstadoIn(vehiculo.getId(),List.of(
                Estado.RECIBIDA,
                Estado.DIAGNOSTICO,
                Estado.EN_REPARACION,
                Estado.LISTA
        ));
    }


    @Test
    void testAgregarServicio(){
        Servicio servicio1 = new Servicio(1L,"Agregar diesel",BigDecimal.valueOf(150),1, new ArrayList<>());
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,2);
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        when(this.servicioRepository.findById(1L)).thenReturn(Optional.of(servicio1));
        when(this.detalleOrdenRepository.findByOrdenServicioIdAndServicioId(1L,agregarServicioDTO.getIdServicio())).thenReturn(Optional.empty());
        when(this.repository.save(any(OrderServicio.class))).thenReturn(servicio);
        OrdenServicioDTO dto = this.servicio.agregarServicio(1L,agregarServicioDTO);
        assertNotNull(dto);
        verify(this.repository,times(1)).findById(1L);
        verify(this.servicioRepository,times(1)).findById(1L);
        verify(this.detalleOrdenRepository,times(1)).findByOrdenServicioIdAndServicioId(1L,agregarServicioDTO.getIdServicio());
    }


    @Test
    void testAgregarServicio_FirstException(){
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,2);
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->this.servicio.agregarServicio(1L,agregarServicioDTO));
        assertEquals("Orden no encontrada", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testAgregarServicio_SecondException(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,2);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.CANCELADA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),new Vehiculo(),new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.agregarServicio(1L,agregarServicioDTO));
        assertEquals("La orden ya no puede ser modificada", exception.getMessage());
    }

    @Test
    void testAgregarServicio_ThirdException(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,2);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.ENTREGADA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),new Vehiculo(),new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.agregarServicio(1L,agregarServicioDTO));
        assertEquals("La orden ya no puede ser modificada", exception.getMessage());
    }

    @Test
    void testAgregarServicio_FourException(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,0);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),new Vehiculo(),new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.agregarServicio(1L,agregarServicioDTO));
        assertEquals("La cantidad debe ser mayor a cero", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void testAgregarServicio_FiveException(){
        Servicio servicio1 = new Servicio(1L,"Agregar diesel",BigDecimal.valueOf(150),1, new ArrayList<>());
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,2);
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        when(this.servicioRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.agregarServicio(1L, agregarServicioDTO));
        assertEquals("Servicio no encontrado", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
        verify(this.servicioRepository,times(1)).findById(1L);
    }


    @Test
    void testAgregarServicio_OptionalCase(){

        Servicio servicio1 = new Servicio(1L,"Agregar diesel",BigDecimal.valueOf(150),1, new ArrayList<>());
        AgregarServicioDTO agregarServicioDTO = new AgregarServicioDTO(1L,2);
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,new ArrayList<>());
        DetalleOrden detalleOrden = new DetalleOrden(1L,2,BigDecimal.valueOf(300),servicio,servicio1);
        List<DetalleOrden> lista = new ArrayList<>();
        lista.add(detalleOrden);
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        when(this.servicioRepository.findById(1L)).thenReturn(Optional.of(servicio1));
        when(this.detalleOrdenRepository.findByOrdenServicioIdAndServicioId(1L,agregarServicioDTO.getIdServicio())).thenReturn(Optional.of(detalleOrden));
        when(this.detalleOrdenRepository.findAllByOrdenServicioId(1L)).thenReturn(lista);
        when(this.detalleOrdenRepository.save(any(DetalleOrden.class))).thenReturn(detalleOrden);
        when(this.repository.save(any(OrderServicio.class))).thenReturn(servicio);
        OrdenServicioDTO dto = this.servicio.agregarServicio(1L,agregarServicioDTO);
        assertNotNull(dto);
        assertEquals(BigDecimal.valueOf(696.00),dto.getTotal().setScale(1, RoundingMode.HALF_UP));
        verify(this.repository,times(1)).findById(1L);
        verify(this.servicioRepository,times(1)).findById(1L);
        verify(this.detalleOrdenRepository,times(1)).findByOrdenServicioIdAndServicioId(1L,agregarServicioDTO.getIdServicio());
        verify(this.repository,times(1)).save(any(OrderServicio.class));
        verify(this.detalleOrdenRepository,times(1)).findAllByOrdenServicioId(1L);
    }



    @Test
    void testCambiarEstado(){
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        CambiarEstadoDTO cambiarEstadoDTO = new CambiarEstadoDTO(Estado.DIAGNOSTICO);
        when(this.repository.save(any(OrderServicio.class))).thenReturn(servicio);
        OrdenServicioDTO dto = this.servicio.cambiarEstado(1L,cambiarEstadoDTO);
        assertNotNull(dto);
        assertEquals(Estado.DIAGNOSTICO, dto.getEstado());
        verify(this.repository,times(1)).findById(1L);
        verify(this.repository,times(1)).save(any(OrderServicio.class));
    }


    @Test
    void testCambiarEstado_FirstException(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        CambiarEstadoDTO cambiarEstadoDTO = new CambiarEstadoDTO(Estado.DIAGNOSTICO);
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.cambiarEstado(1L,cambiarEstadoDTO));
        assertEquals("Orden no encontrada", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testCambiarEstado_SecondException(){
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        CambiarEstadoDTO cambiarEstadoDTO = new CambiarEstadoDTO(Estado.ENTREGADA);
        when(this.detalleOrdenRepository.findAllByOrdenServicioId(1L)).thenReturn(new ArrayList<>());
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.cambiarEstado(1L,cambiarEstadoDTO));
        assertEquals("No se puede entregar una orden sin servicios", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
        verify(this.detalleOrdenRepository,times(1)).findAllByOrdenServicioId(1L);
    }



    @Test
    void testCambiarEstado_ThirdException(){
        Servicio servicio1 = new Servicio(1L,"Agregar diesel",BigDecimal.valueOf(150),1, new ArrayList<>());
        Vehiculo vehiculo = new Vehiculo(1L,"ABC-123-A",2005,1658L,new Cliente(),new ArrayList<>());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaIngreso = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        OrderServicio servicio =  new OrderServicio(1L,fechaIngreso,fechaSalida, Estado.RECIBIDA, BigDecimal.valueOf(450),
                BigDecimal.valueOf(0.16),BigDecimal.valueOf(550),vehiculo,new ArrayList<>());
        when(this.repository.findById(1L)).thenReturn(Optional.of(servicio));
        DetalleOrden detalleOrden = new DetalleOrden(1L,2,BigDecimal.valueOf(300),servicio,servicio1);
        List<DetalleOrden> lista = new ArrayList<>();
        lista.add(detalleOrden);
        CambiarEstadoDTO cambiarEstadoDTO = new CambiarEstadoDTO(Estado.EN_REPARACION);
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> this.servicio.cambiarEstado(1L, cambiarEstadoDTO));
        assertEquals("Transición invalida: No se puede pasar de " + Estado.RECIBIDA + " a " + Estado.EN_REPARACION, exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }









}
