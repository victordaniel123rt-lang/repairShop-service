package com.vdgarcia.repairShop_service.repository;

import com.vdgarcia.repairShop_service.model.entity.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {

    Optional<DetalleOrden> findByOrdenIdAndServicioId(Long idOrden,Long servicioId);
    List<DetalleOrden> findAllByOrdenId(Long id);
}
