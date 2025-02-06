package com.example.Mascotas.Repositorio;

import com.example.Mascotas.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
