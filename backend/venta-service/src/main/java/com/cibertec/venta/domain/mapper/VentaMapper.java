package com.cibertec.venta.domain.mapper;

import com.cibertec.venta.api.dto.DetalleVentaResponseDto;
import com.cibertec.venta.api.dto.VentaResponseDto;
import com.cibertec.venta.data.entity.DetalleVenta;
import com.cibertec.venta.data.entity.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    @Mapping(target = "clienteNombre", ignore = true)
    VentaResponseDto toResponseDto(Venta venta);

    List<VentaResponseDto> toResponseDtoList(List<Venta> ventas);

    DetalleVentaResponseDto toDetalleResponseDto(DetalleVenta detalle);

    List<DetalleVentaResponseDto> toDetalleResponseDtoList(List<DetalleVenta> detalles);
}
