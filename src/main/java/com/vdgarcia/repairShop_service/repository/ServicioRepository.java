package com.vdgarcia.repairShop_service.repository;

import com.vdgarcia.repairShop_service.model.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    Optional<Servicio> findByNombre(String nombre);

}
