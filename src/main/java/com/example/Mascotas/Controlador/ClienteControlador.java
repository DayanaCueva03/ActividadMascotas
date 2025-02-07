package com.example.Mascotas.Controlador;

import com.example.Mascotas.Entidad.Cliente;
import com.example.Mascotas.Entidad.Mascota;
import com.example.Mascotas.Servicio.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;


@Controller
public class ClienteControlador {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public String vista() {
        return "index";
    }


    // LISTADO DE CLIENTES
    @GetMapping("/vistaClientes")
    public String vistaClientes(Model model) {
        model.addAttribute("clientes", clienteService.verCliente());
        return "pages/vistaCliente";
    }

    @GetMapping("/listaClientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "pages/listadoClientes";
    }

    // Crear un nuevo cliente (mostrar formulario)
    @GetMapping("/vistaCliente")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/formularioCliente";
    }

    // Crear nuevo cliente (guardar)
    @PostMapping("/guardarClientes")
    public String crearCliente(Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/listaClientes";
    }


    // ACTUALIZAR: Mostrar el formulario con los datos del cliente para editar
    @GetMapping("/editarClientes/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.buscarCliente(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "pages/formularioCliente";
        }
        return "redirect:/listaClientes";
    }

    // ELIMINAR: Elimina un cliente por ID
    @GetMapping("/eliminarClientes/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/listaClientes";
    }

}
