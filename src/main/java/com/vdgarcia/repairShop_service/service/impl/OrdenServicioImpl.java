package com.vdgarcia.repairShop_service.service.impl;
import com.vdgarcia.repairShop_service.mapper.Mapper;
import com.vdgarcia.repairShop_service.model.dto.AgregarServicioDTO;
import com.vdgarcia.repairShop_service.model.dto.CambiarEstadoDTO;
import com.vdgarcia.repairShop_service.model.dto.OrdenServicioDTO;
import com.vdgarcia.repairShop_service.model.entity.*;
import com.vdgarcia.repairShop_service.model.enums.Estado;
import com.vdgarcia.repairShop_service.repository.DetalleOrdenRepository;
import com.vdgarcia.repairShop_service.repository.OrdenRepository;
import com.vdgarcia.repairShop_service.repository.ServicioRepository;
import com.vdgarcia.repairShop_service.service.intef.OrdenServicioService;
import com.vdgarcia.repairShop_service.service.intef.VehiculoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrdenServicioImpl implements OrdenServicioService {
    private final OrdenRepository repository;
    private final VehiculoService vehiculoService;
    private final ServicioRepository servicioRepository;
    private final DetalleOrdenRepository detalleOrdenRepository;
    private static final BigDecimal IVA =
            new BigDecimal("0.16");

    private static final BigDecimal DESCUENTO =
            new BigDecimal("0.10");

    @Override
    public List<OrdenServicioDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toordenServicioDTO).toList();
    }

    @Override
    public OrdenServicioDTO obtenerPorId(Long id) {
        return Mapper.toordenServicioDTO(repository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Orden no encontrada")
        ));
    }

    @Override
    public OrdenServicioDTO crear(OrdenServicioDTO dto) {
        Vehiculo vehiculo = Mapper.tovehiculo(vehiculoService.obtenerPorId(dto.getVehiculo()));
        Optional<OrderServicio> optional = vehiculo.getOrdenes().stream().findAny();
        if(optional.isPresent()){
            OrderServicio orden = optional.get();
            throw  new IllegalArgumentException("El vehículo cuente actualmente con una orden activa en estado: " + orden.getEstado());
        }
        OrderServicio orden = Mapper.toOrdenServicio(dto);
        OrderServicio creada = repository.save(orden);
        log.info("La orden ha sido creada de manera exitosa {}", creada.getId());
        return Mapper.toordenServicioDTO(creada);
    }

    @Override
    @Transactional
    public OrdenServicioDTO agregarServicio(Long id, AgregarServicioDTO dto) {
        OrderServicio orden = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Orden no encontrada")
        );
        BigDecimal subtotal;
        Estado estado = orden.getEstado();
        if (estado.equals(Estado.CANCELADA) || estado.equals(Estado.ENTREGADA)) {
            throw new IllegalArgumentException("La orden ya no puede ser modificada");
        }
        if (dto.getCantidad() < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        Servicio servicio = servicioRepository.findById(dto.getIdServicio()).orElseThrow(
                () -> new IllegalArgumentException("Servicio no encontrado")
        );
        Optional<DetalleOrden> existe = detalleOrdenRepository.findByOrdenServicioIdAndServicioId(id, dto.getIdServicio());
        if (existe.isPresent()) {
            DetalleOrden detalle = existe.get();
            detalle.setCantidad(detalle.getCantidad() + dto.getCantidad());
            detalle.setSubtotal(servicio.getPrecioBase().multiply(BigDecimal.valueOf(detalle.getCantidad())));
            detalleOrdenRepository.save(detalle);

        } else {
            DetalleOrden detalle = DetalleOrden.builder()
                    .cantidad(dto.getCantidad())
                    .subtotal(servicio.getPrecioBase().multiply(BigDecimal.valueOf(dto.getCantidad())))
                    .ordenServicio(orden)
                    .servicio(servicio)
                    .build();
            detalleOrdenRepository.save(detalle);
        }
        subtotal = detalleOrdenRepository.findAllByOrdenServicioId(id).stream().map(DetalleOrden::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        if(subtotal.compareTo(BigDecimal.valueOf(20000))>0){
            subtotal = subtotal.subtract(subtotal.multiply(DESCUENTO));
        }

        orden.setSubtotal(subtotal);
        orden.setIva(subtotal.multiply(IVA).setScale(2, RoundingMode.HALF_UP));
        orden.setTotal(subtotal.add(orden.getIva()));
        OrderServicio guardada = repository.save(orden);
        return Mapper.toordenServicioDTO(guardada);
    }

    @Override
    public OrdenServicioDTO cambiarEstado(Long id, CambiarEstadoDTO dto) {
        OrderServicio orden = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Orden no encontrada")
        );
        Estado estado = orden.getEstado();
        if(dto.getNuevoEstado() == Estado.ENTREGADA
                && detalleOrdenRepository.findAllByOrdenServicioId(id).isEmpty()){

            throw new IllegalArgumentException(
                    "No se puede entregar una orden sin servicios");
        }
        if(!estado.puedeTransicionarA(dto.getNuevoEstado())){
            throw new IllegalArgumentException(String.format("Transición invalida: No se puede pasar de %s a %s", estado, dto.getNuevoEstado()));
        }

        orden.setEstado(dto.getNuevoEstado());
        repository.save(orden);
        OrderServicio guardado = repository.save(orden);
        return Mapper.toordenServicioDTO(guardado);
    }


}
