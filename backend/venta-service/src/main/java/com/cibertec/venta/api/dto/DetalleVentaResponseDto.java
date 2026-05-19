package com.cibertec.venta.api.dto;

import java.math.BigDecimal;

public record DetalleVentaResponseDto(
        Integer id,
        Integer productoId,
        String productoDescripcion,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal
) {
}
