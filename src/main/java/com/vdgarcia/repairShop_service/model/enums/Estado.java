package com.vdgarcia.repairShop_service.model.enums;

import java.util.EnumSet;
public enum Estado {

    RECIBIDA,
    DIAGNOSTICO,
    EN_REPARACION,
    LISTA,
    ENTREGADA,
    CANCELADA;

    public boolean puedeTransicionarA(Estado nuevoEstado) {

        if (this == CANCELADA || this == ENTREGADA)
            return false;

        return switch (this) {

            case RECIBIDA ->
                    nuevoEstado == DIAGNOSTICO ||
                            nuevoEstado == CANCELADA;

            case DIAGNOSTICO ->
                    nuevoEstado == EN_REPARACION ||
                            nuevoEstado == CANCELADA;

            case EN_REPARACION ->
                    nuevoEstado == LISTA ||
                            nuevoEstado == CANCELADA;

            case LISTA ->
                    nuevoEstado == ENTREGADA ||
                            nuevoEstado == CANCELADA;

            default -> false;
        };
    }
}