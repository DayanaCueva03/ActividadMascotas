package com.example.Mascotas.Repositorio;

import com.example.Mascotas.Entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
