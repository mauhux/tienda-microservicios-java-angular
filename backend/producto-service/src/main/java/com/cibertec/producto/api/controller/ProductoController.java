package com.cibertec.producto.api.controller;

import com.cibertec.producto.api.dto.ProductoRequestDto;
import com.cibertec.producto.api.dto.ProductoResponseDto;
import com.cibertec.producto.domain.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponseDto> findAll() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ProductoResponseDto findById(@PathVariable Integer id) {
        return productoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto create(@Valid @RequestBody ProductoRequestDto dto) {
        return productoService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductoResponseDto update(
            @PathVariable Integer id,
            @Valid @RequestBody ProductoRequestDto dto
    ) {
        return productoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productoService.delete(id);
    }
}
