package com.cibertec.venta.domain.service;

import com.cibertec.venta.api.dto.VentaRequestDto;
import com.cibertec.venta.api.dto.VentaResponseDto;

import java.util.List;

public interface VentaService {

    List<VentaResponseDto> findAll();

    VentaResponseDto findById(Integer id);

    List<VentaResponseDto> findByClienteId(Integer clienteId);

    VentaResponseDto create(VentaRequestDto dto);

    void delete(Integer id);
}
