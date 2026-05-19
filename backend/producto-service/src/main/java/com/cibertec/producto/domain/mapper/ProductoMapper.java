package com.cibertec.producto.domain.mapper;

import com.cibertec.producto.api.dto.ProductoRequestDto;
import com.cibertec.producto.api.dto.ProductoResponseDto;
import com.cibertec.producto.data.entity.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    Producto toEntity(ProductoRequestDto dto);

    ProductoResponseDto toResponseDto(Producto producto);

    List<ProductoResponseDto> toResponseDtoList(List<Producto> productos);
}
