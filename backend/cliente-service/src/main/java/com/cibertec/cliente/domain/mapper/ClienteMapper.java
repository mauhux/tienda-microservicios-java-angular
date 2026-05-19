package com.cibertec.cliente.domain.mapper;

import com.cibertec.cliente.api.dto.ClienteRequestDto;
import com.cibertec.cliente.api.dto.ClienteResponseDto;
import com.cibertec.cliente.data.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteRequestDto dto);

    ClienteResponseDto toResponseDto(Cliente cliente);

    List<ClienteResponseDto> toResponseDtoList(List<Cliente> clientes);
}
