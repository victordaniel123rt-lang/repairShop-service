package com.vdgarcia.repairShop_service.repository;


import com.vdgarcia.repairShop_service.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
