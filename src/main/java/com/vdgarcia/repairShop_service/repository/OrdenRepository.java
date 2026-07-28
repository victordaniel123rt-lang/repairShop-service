package com.vdgarcia.repairShop_service.repository;


import com.vdgarcia.repairShop_service.model.entity.OrderServicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<OrderServicio,Long> {
}
