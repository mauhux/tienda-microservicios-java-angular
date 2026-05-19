package com.cibertec.venta.domain.service.impl;

import com.cibertec.venta.api.client.ClienteClient;
import com.cibertec.venta.api.client.ProductoClient;
import com.cibertec.venta.api.dto.*;
import com.cibertec.venta.api.exception.BadRequestException;
import com.cibertec.venta.api.exception.ResourceNotFoundException;
import com.cibertec.venta.data.entity.DetalleVenta;
import com.cibertec.venta.data.entity.Venta;
import com.cibertec.venta.data.repository.VentaRepository;
import com.cibertec.venta.domain.mapper.VentaMapper;
import com.cibertec.venta.domain.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final ClienteClient clienteClient;
    private final ProductoClient productoClient;

    @Override
    public List<VentaResponseDto> findAll() {
        return ventaRepository.findAll()
                .stream()
                .map(this::toResponseConCliente)
                .toList();
    }

    @Override
    public VentaResponseDto findById(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + id));

        return toResponseConCliente(venta);
    }

    @Override
    public List<VentaResponseDto> findByClienteId(Integer clienteId) {
        return ventaRepository.findByClienteId(clienteId)
                .stream()
                .map(this::toResponseConCliente)
                .toList();
    }

    @Override
    @Transactional
    public VentaResponseDto create(VentaRequestDto dto) {

        ClienteResponseDto cliente = clienteClient.findById(dto.clienteId());

        Venta venta = new Venta();
        venta.setClienteId(cliente.id());
        venta.setFecha(LocalDate.now());
        venta.setEstado("REGISTRADA");

        List<DetalleVenta> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DetalleVentaRequestDto detalleDto : dto.detalles()) {

            ProductoResponseDto producto = productoClient.findById(detalleDto.productoId());

            if (detalleDto.cantidad() <= 0) {
                throw new BadRequestException("La cantidad debe ser mayor a cero");
            }

            if (producto.stock() < detalleDto.cantidad()) {
                throw new BadRequestException("Stock insuficiente para el producto: " + producto.descripcion());
            }

            BigDecimal precioUnitario = producto.precioUnitario();
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(detalleDto.cantidad()));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProductoId(producto.id());
            detalle.setProductoDescripcion(producto.descripcion());
            detalle.setCantidad(detalleDto.cantidad());
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setSubtotal(subtotal);

            detalles.add(detalle);
            total = total.add(subtotal);
        }

        venta.setTotal(total);
        venta.setDetalles(detalles);

        Venta ventaGuardada = ventaRepository.save(venta);

        return toResponseConCliente(ventaGuardada);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + id));

        ventaRepository.delete(venta);
    }

    private VentaResponseDto toResponseConCliente(Venta venta) {
        ClienteResponseDto cliente = clienteClient.findById(venta.getClienteId());

        String clienteNombre = cliente.nombres() + " " + cliente.apellidos();

        return new VentaResponseDto(
                venta.getId(),
                venta.getClienteId(),
                clienteNombre,
                venta.getFecha(),
                venta.getTotal(),
                venta.getEstado(),
                ventaMapper.toDetalleResponseDtoList(venta.getDetalles()),
                venta.getCreatedAt(),
                venta.getUpdatedAt()
        );
    }
}
