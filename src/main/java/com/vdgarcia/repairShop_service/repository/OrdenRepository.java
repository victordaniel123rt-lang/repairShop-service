package com.vdgarcia.repairShop_service.repository;


import com.vdgarcia.repairShop_service.model.entity.OrderServicio;
import com.vdgarcia.repairShop_service.model.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdenRepository extends JpaRepository<OrderServicio,Long> {
    Optional<OrderServicio> findFirstByVehiculoIdAndEstadoIn(
            Long vehiculoId,
            List<Estado> estados);
}
