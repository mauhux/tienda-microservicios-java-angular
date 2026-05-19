package com.cibertec.venta.api.dto;

import java.time.LocalDateTime;

public record ClienteResponseDto(
        Integer id,
        String nombres,
        String apellidos,
        String dni,
        String email,
        String telefono,
        String direccion,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
