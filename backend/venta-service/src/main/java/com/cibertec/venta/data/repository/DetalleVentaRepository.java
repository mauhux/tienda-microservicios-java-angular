package com.cibertec.venta.data.repository;

import com.cibertec.venta.data.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    // Listar detalle por venta
    List<DetalleVenta> findByVentaId(Integer ventaId);
}
