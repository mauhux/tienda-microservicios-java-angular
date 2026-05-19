package com.cibertec.venta.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDto(
        Integer id,
        Integer clienteId,
        String clienteNombre,
        LocalDate fecha,
        BigDecimal total,
        String estado,
        List<DetalleVentaResponseDto> detalles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
