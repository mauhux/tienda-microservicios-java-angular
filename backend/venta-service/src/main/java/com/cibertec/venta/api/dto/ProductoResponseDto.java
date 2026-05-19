package com.cibertec.venta.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductoResponseDto(
        Integer id,
        String descripcion,
        BigDecimal precioUnitario,
        Integer stock,
        String estado,
        String imgUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
