package com.vdgarcia.repairShop_service.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cliente")
@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculos;

}
