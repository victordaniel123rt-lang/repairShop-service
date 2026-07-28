package com.vdgarcia.repairShop_service.repository;

import com.vdgarcia.repairShop_service.model.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Long>{ }
