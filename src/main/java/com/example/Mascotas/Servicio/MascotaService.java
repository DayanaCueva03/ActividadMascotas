package com.example.Mascotas.Servicio;

import com.example.Mascotas.Entidad.Mascota;
import com.example.Mascotas.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> verMascotas() {
        return mascotaRepository.findAll();
    }
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    public void guardarMascota(Mascota mascota) {
        mascotaRepository.save(mascota);
    }

    public Optional<Mascota> buscarMascota(Long id) {
        return mascotaRepository.findById(id);
    }

    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}