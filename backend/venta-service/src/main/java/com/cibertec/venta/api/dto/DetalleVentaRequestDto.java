package com.cibertec.venta.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DetalleVentaRequestDto(

        @NotNull
        Integer productoId,

        @NotNull
        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        Integer cantidad
) {
}
