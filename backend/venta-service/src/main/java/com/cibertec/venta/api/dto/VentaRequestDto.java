package com.cibertec.venta.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VentaRequestDto(

        @NotNull
        Integer clienteId,

        @NotEmpty
        List<@Valid DetalleVentaRequestDto> detalles
) {
}
