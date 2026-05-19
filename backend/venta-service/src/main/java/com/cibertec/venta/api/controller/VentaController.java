package com.cibertec.venta.api.controller;

import com.cibertec.venta.api.dto.VentaRequestDto;
import com.cibertec.venta.api.dto.VentaResponseDto;
import com.cibertec.venta.domain.service.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public List<VentaResponseDto> findAll() {
        return ventaService.findAll();
    }

    @GetMapping("/{id}")
    public VentaResponseDto findById(@PathVariable Integer id) {
        return ventaService.findById(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<VentaResponseDto> findByClienteId(@PathVariable Integer clienteId) {
        return ventaService.findByClienteId(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDto create(@Valid @RequestBody VentaRequestDto dto) {
        return ventaService.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        ventaService.delete(id);
    }
}
