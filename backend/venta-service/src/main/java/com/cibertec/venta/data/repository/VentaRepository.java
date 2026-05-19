package com.cibertec.venta.data.repository;

import com.cibertec.venta.data.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<com.cibertec.venta.data.entity.Venta, Integer> {

    // Listar ventas por cliente
    List<Venta> findByClienteId(Integer clienteId);
}
