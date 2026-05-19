package com.cibertec.cliente.api.controller;

import com.cibertec.cliente.api.dto.ClienteRequestDto;
import com.cibertec.cliente.api.dto.ClienteResponseDto;
import com.cibertec.cliente.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDto> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ClienteResponseDto findById(@PathVariable Integer id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto create(@Valid @RequestBody ClienteRequestDto dto) {
        return clienteService.create(dto);
    }

    @PutMapping("/{id}")
    public ClienteResponseDto update(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteRequestDto dto
    ) {
        return clienteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clienteService.delete(id);
    }
}
