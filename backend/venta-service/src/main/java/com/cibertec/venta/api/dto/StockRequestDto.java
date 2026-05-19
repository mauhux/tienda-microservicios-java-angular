package com.cibertec.venta.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockRequestDto(

        //@NotNull
        //@Min(value = 1, message = "La cantidad debe ser mayor a 0")
        Integer cantidad
) {
}
