package com.cibertec.producto.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductoRequestDto(

        @NotBlank
        String descripcion,

        @NotNull
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
        BigDecimal precioUnitario,

        @NotNull
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,

        @NotBlank
        String estado,

        String imgUrl
) {
}
