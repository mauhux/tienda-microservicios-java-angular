package com.cibertec.cliente.data.repository;

import com.cibertec.cliente.data.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Optional evita null haciendo validacion antes de insertar
    // Buscar por DNI (útil para validaciones)
    Optional<Cliente> findByDni(String dni);

    // Buscar por email
    Optional<Cliente> findByEmail(String email);
}
