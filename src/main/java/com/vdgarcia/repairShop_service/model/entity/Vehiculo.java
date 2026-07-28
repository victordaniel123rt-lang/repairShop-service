package com.vdgarcia.repairShop_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vehiculo")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private Integer anio;
    private Long kilometraje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<OrderServicio> ordenes;



}
