package com.example.Mascotas.Repositorio;

import com.example.Mascotas.Entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
