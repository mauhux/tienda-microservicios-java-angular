package com.cibertec.producto.data.repository;

import com.cibertec.producto.data.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Listar solo productos disponibles
    List<Producto> findByEstado(String estado);

    // Buscar productos con stock mayor a X
    List<Producto> findByStockGreaterThan(Integer stock);
}
