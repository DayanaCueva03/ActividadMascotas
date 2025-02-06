package com.example.Mascotas.Servicio;

import com.example.Mascotas.Entidad.Producto;
import com.example.Mascotas.Repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }


    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public Optional<Producto> buscarProducto(Long id) {
        return productoRepository.findById(id);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
