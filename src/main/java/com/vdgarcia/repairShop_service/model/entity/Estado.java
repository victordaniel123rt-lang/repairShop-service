package com.vdgarcia.repairShop_service.model.entity;

import java.util.EnumSet;

public enum Estado {
    ENTREGADA(),
    LISTA(ENTREGADA),
    EN_REPARACION(LISTA),
    DIAGNOSTICO(EN_REPARACION),
    RECIBIDA(DIAGNOSTICO),
    CANCELADA();

    private final EnumSet<Estado> permitidos;

    Estado(Estado... permitidos) {
        if (permitidos.length == 0) {
            this.permitidos = EnumSet.noneOf(Estado.class);
        } else {
            this.permitidos = EnumSet.copyOf(EnumSet.of(permitidos[0], permitidos));
        }
    }

    public boolean puedeTransicionarA(Estado nuevoEstado) {
        if (this == CANCELADA || this == ENTREGADA) {
            return false;
        }

        if (nuevoEstado == CANCELADA) {
            return true;
        }
        return this.permitidos.contains(nuevoEstado);
    }
}
