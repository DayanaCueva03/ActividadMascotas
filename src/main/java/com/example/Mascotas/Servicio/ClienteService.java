package com.example.Mascotas.Servicio;

import com.example.Mascotas.Entidad.Cliente;
import com.example.Mascotas.Repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }


    public Optional<Cliente> buscarCliente(Long id) {
        return clienteRepository.findById(id);
    }


    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
