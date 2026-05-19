package com.cibertec.producto.domain.service.impl;

import com.cibertec.producto.api.dto.ProductoRequestDto;
import com.cibertec.producto.api.dto.ProductoResponseDto;
import com.cibertec.producto.api.exception.BadRequestException;
import com.cibertec.producto.api.exception.ResourceNotFoundException;
import com.cibertec.producto.data.entity.Producto;
import com.cibertec.producto.data.repository.ProductoRepository;
import com.cibertec.producto.domain.mapper.ProductoMapper;
import com.cibertec.producto.domain.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public List<ProductoResponseDto> findAll() {
        return productoMapper.toResponseDtoList(productoRepository.findAll());
    }

    @Override
    public ProductoResponseDto findById(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        return productoMapper.toResponseDto(producto);
    }

    @Override
    public ProductoResponseDto create(ProductoRequestDto dto) {
        Producto producto = productoMapper.toEntity(dto);
        Producto productoGuardado = productoRepository.save(producto);

        return productoMapper.toResponseDto(productoGuardado);
    }

    @Override
    public ProductoResponseDto update(Integer id, ProductoRequestDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        producto.setDescripcion(dto.descripcion());
        producto.setPrecioUnitario(dto.precioUnitario());
        producto.setStock(dto.stock());
        producto.setEstado(dto.estado());
        producto.setImgUrl(dto.imgUrl());

        Producto productoActualizado = productoRepository.save(producto);

        return productoMapper.toResponseDto(productoActualizado);
    }

    @Override
    public void delete(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        productoRepository.delete(producto);
    }

    @Override
    @Transactional
    public void descontarStock(Integer id, Integer cantidad) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto no encontrado con ID: " + id
                        )
                );

        if (cantidad <= 0) {
            throw new BadRequestException(
                    "La cantidad debe ser mayor a cero"
            );
        }

        if (producto.getStock() < cantidad) {
            throw new BadRequestException(
                    "Stock insuficiente para el producto: "
                            + producto.getDescripcion()
            );
        }

        producto.setStock(producto.getStock() - cantidad);

        productoRepository.save(producto);
    }
}
