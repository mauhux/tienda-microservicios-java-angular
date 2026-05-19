package com.cibertec.cliente.domain.service.impl;

import com.cibertec.cliente.api.dto.ClienteRequestDto;
import com.cibertec.cliente.api.dto.ClienteResponseDto;
import com.cibertec.cliente.api.exception.BadRequestException;
import com.cibertec.cliente.api.exception.ResourceNotFoundException;
import com.cibertec.cliente.data.entity.Cliente;
import com.cibertec.cliente.data.repository.ClienteRepository;
import com.cibertec.cliente.domain.mapper.ClienteMapper;
import com.cibertec.cliente.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<ClienteResponseDto> findAll() {
        return clienteMapper.toResponseDtoList(clienteRepository.findAll());
    }

    @Override
    public ClienteResponseDto findById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        return clienteMapper.toResponseDto(cliente);
    }

    @Override
    public ClienteResponseDto create(ClienteRequestDto dto) {

        if (clienteRepository.findByDni(dto.dni()).isPresent()) {
            throw new BadRequestException("Ya existe un cliente con el DNI: " + dto.dni());
        }

        if (clienteRepository.findByEmail(dto.email()).isPresent()) {
            throw new BadRequestException("Ya existe un cliente con el email: " + dto.email());
        }

        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente clienteGuardado = clienteRepository.save(cliente);

        return clienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    public ClienteResponseDto update(Integer id, ClienteRequestDto dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        cliente.setNombres(dto.nombres());
        cliente.setApellidos(dto.apellidos());
        cliente.setDni(dto.dni());
        cliente.setEmail(dto.email());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());

        Cliente clienteActualizado = clienteRepository.save(cliente);

        return clienteMapper.toResponseDto(clienteActualizado);
    }

    @Override
    public void delete(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        clienteRepository.delete(cliente);
    }
}
