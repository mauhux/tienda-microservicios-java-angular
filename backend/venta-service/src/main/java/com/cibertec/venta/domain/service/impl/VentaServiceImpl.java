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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final ClienteClient clienteClient;
    private final ProductoClient productoClient;

    @Override
    public List<VentaResponseDto> findAll() {
        log.info("Listando todas las ventas");

        return ventaRepository.findAll()
                .stream()
                .map(this::toResponseConCliente)
                .toList();
    }

    @Override
    public VentaResponseDto findById(Integer id) {
        log.info("Buscando venta con ID: {}", id);

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + id));

        return toResponseConCliente(venta);
    }

    @Override
    public List<VentaResponseDto> findByClienteId(Integer clienteId) {
        log.info("Listando ventas del cliente ID: {}", clienteId);

        // Llamada Feign solo para demostrar comunicación con cliente-service
        log.info("Llamando a cliente-service mediante ClienteClient.findById({})", clienteId);
        ClienteResponseDto cliente = clienteClient.findById(clienteId);
        log.info("Cliente encontrado desde cliente-service: {} {}", cliente.nombres(), cliente.apellidos());

        return ventaRepository.findByClienteId(clienteId)
                .stream()
                .map(venta -> toResponseConCliente(venta, cliente))
                .toList();
    }

    @Override
    @Transactional
    public VentaResponseDto create(VentaRequestDto dto) {

        log.info("Registrando nueva venta para cliente ID: {}", dto.clienteId());

        log.info("Llamando a cliente-service mediante ClienteClient.findById({})", dto.clienteId());
        ClienteResponseDto cliente = clienteClient.findById(dto.clienteId());
        log.info("Cliente validado: {} {}", cliente.nombres(), cliente.apellidos());

        Venta venta = new Venta();
        venta.setClienteId(cliente.id());
        venta.setFecha(LocalDate.now());
        venta.setEstado("REGISTRADA");

        List<DetalleVenta> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DetalleVentaRequestDto detalleDto : dto.detalles()) {

            if (detalleDto.cantidad() <= 0) {
                throw new BadRequestException("La cantidad debe ser mayor a cero");
            }

            log.info("Llamando a producto-service mediante ProductoClient.findById({})", detalleDto.productoId());
            ProductoResponseDto producto = productoClient.findById(detalleDto.productoId());
            log.info("Producto encontrado: {} | Stock actual: {}", producto.descripcion(), producto.stock());

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

            log.info("Llamando a producto-service para descontar stock. Producto ID: {}, cantidad: {}",
                    producto.id(), detalleDto.cantidad());

            //productoClient.descontarStock(producto.id(), detalleDto.cantidad());

            log.info("Stock descontado correctamente para el producto: {}", producto.descripcion());
        }

        venta.setTotal(total);
        venta.setDetalles(detalles);

        Venta ventaGuardada = ventaRepository.save(venta);

        log.info("Venta registrada correctamente con ID: {}", ventaGuardada.getId());

        return toResponseConCliente(ventaGuardada, cliente);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        log.info("Eliminando venta con ID: {}", id);

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + id));

        ventaRepository.delete(venta);

        log.info("Venta eliminada correctamente con ID: {}", id);
    }

    private VentaResponseDto toResponseConCliente(Venta venta) {
        log.info("Llamando a cliente-service mediante ClienteClient.findById({})", venta.getClienteId());

        ClienteResponseDto cliente = clienteClient.findById(venta.getClienteId());

        log.info("Cliente obtenido desde cliente-service: {} {}", cliente.nombres(), cliente.apellidos());

        return toResponseConCliente(venta, cliente);
    }

    private VentaResponseDto toResponseConCliente(Venta venta, ClienteResponseDto cliente) {

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
