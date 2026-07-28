package com.vdgarcia.repairShop_service.mapper;

import com.vdgarcia.repairShop_service.model.dto.*;
import com.vdgarcia.repairShop_service.model.entity.*;

import java.util.List;

public class Mapper {

    public static ClienteDTO toClienteDTO(Cliente entity){
        if(entity==null) return null;
        List<VehiculoDTO> lista = entity.getVehiculos().stream().map(Mapper::tovehiculoDTO).toList();
        return ClienteDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .correo(entity.getCorreo())
                .telefono(entity.getTelefono())
                .apellido(entity.getApellido())
                .vehiculos(lista)
                .build();
    }


    public static VehiculoDTO tovehiculoDTO(Vehiculo entity){
        if(entity==null) return null;

        return VehiculoDTO.builder()
                .id(entity.getId())
                .anio(entity.getAnio())
                .placa(entity.getPlaca())
                .cliente(entity.getCliente().getId())
                .kilometraje(entity.getKilometraje())
                .build();

    }


    public static ServicioDTO toServicioDTO(Servicio entity){
        if(entity==null) return null;
        List<DetalleOrdenDTO> lista = entity.getDetalles().stream().map(Mapper::toDetalleOrdenDTO).toList();
        return ServicioDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .duracionHoras(entity.getDuracionHoras())
                .precioBase(entity.getPrecioBase())
                .detalles(lista)
                .build();

    }

    public static DetalleOrdenDTO toDetalleOrdenDTO(DetalleOrden entity){
        if(entity==null) return null;
        return DetalleOrdenDTO.builder()
                .id(entity.getId())
                .subtotal(entity.getSubtotal())
                .cantidad(entity.getCantidad())
                .servicio(entity.getServicio().getId())
                .orden(entity.getOrdenServicio().getId())
                .build();
    }


    public static OrdenServicioDTO toordenServicioDTO(OrderServicio entity){
        if(entity==null) return null;
        List<DetalleOrdenDTO> lista = entity.getDetalles().stream().map(Mapper::toDetalleOrdenDTO).toList();
        return OrdenServicioDTO.builder()
                .id(entity.getId())
                .total(entity.getTotal())
                .subtotal(entity.getSubtotal())
                .fechaEntrega(entity.getFechaEntrega())
                .fechaIngreso(entity.getFechaIngreso())
                .vehiculo(entity.getVehiculo().getId())
                .iva(entity.getIva())
                .estado(entity.getEstado())
                .detalles(lista)
                .build();
    }


    public static Cliente toCliente(ClienteDTO dto){
        if(dto==null) return null;
        List<Vehiculo> lista = dto.getVehiculos().stream().map(Mapper::tovehiculo).toList();
        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .apellido(dto.getApellido())
                .vehiculos(lista)
                .build();
    }

    public static Vehiculo tovehiculo(VehiculoDTO dto){
        if(dto==null) return null;

        return Vehiculo.builder()
                .id(dto.getId())
                .anio(dto.getAnio())
                .placa(dto.getPlaca())
                .cliente(Cliente.builder().id(dto.getCliente()).build())
                .kilometraje(dto.getKilometraje())
                .build();

    }


    public static Servicio toServicio(ServicioDTO dto){
        if(dto==null) return null;
        List<DetalleOrden> lista = dto.getDetalles().stream().map(Mapper::toDetalleOrden).toList();
        return Servicio.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .duracionHoras(dto.getDuracionHoras())
                .precioBase(dto.getPrecioBase())
                .detalles(lista)
                .build();

    }


    public static DetalleOrden toDetalleOrden(DetalleOrdenDTO dto){
        if(dto==null) return null;
        return DetalleOrden.builder()
                .id(dto.getId())
                .subtotal(dto.getSubtotal())
                .cantidad(dto.getCantidad())
                .servicio(Servicio.builder().id(dto.getServicio()).build())
                .ordenServicio(OrderServicio.builder().id(dto.getOrden()).build())
                .build();
    }



    public static OrderServicio toOrdenServicio(OrdenServicioDTO dto){
        if(dto==null) return null;
        List<DetalleOrden> lista = dto.getDetalles().stream().map(Mapper::toDetalleOrden).toList();
        return OrderServicio.builder()
                .id(dto.getId())
                .total(dto.getTotal())
                .subtotal(dto.getSubtotal())
                .fechaEntrega(dto.getFechaEntrega())
                .fechaIngreso(dto.getFechaIngreso())
                .vehiculo(Vehiculo.builder().id(dto.getVehiculo()).build())
                .iva(dto.getIva())
                .estado(dto.getEstado())
                .detalles(lista)
                .build();
    }


    public static void updateCliente(ClienteDTO dto, Cliente entity){
        if(dto==null || entity==null) return;

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setCorreo(dto.getCorreo());
        entity.setTelefono(dto.getTelefono());

    }











}
