package com.cibertec.cliente.domain.service;

import com.cibertec.cliente.api.dto.ClienteRequestDto;
import com.cibertec.cliente.api.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {

    List<ClienteResponseDto> findAll();

    ClienteResponseDto findById(Integer id);

    ClienteResponseDto create(ClienteRequestDto dto);

    ClienteResponseDto update(Integer id, ClienteRequestDto dto);

    void delete(Integer id);
}
