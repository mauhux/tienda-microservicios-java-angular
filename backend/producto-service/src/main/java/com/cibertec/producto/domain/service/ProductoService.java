package com.cibertec.producto.domain.service;

import com.cibertec.producto.api.dto.ProductoRequestDto;
import com.cibertec.producto.api.dto.ProductoResponseDto;

import java.util.List;

public interface ProductoService {

    List<ProductoResponseDto> findAll();

    ProductoResponseDto findById(Integer id);

    ProductoResponseDto create(ProductoRequestDto dto);

    ProductoResponseDto update(Integer id, ProductoRequestDto dto);

    void delete(Integer id);
}
