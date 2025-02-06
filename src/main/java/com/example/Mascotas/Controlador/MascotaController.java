package com.example.Mascotas.Controlador;

import com.example.Mascotas.Entidad.Cliente;
import com.example.Mascotas.Entidad.Mascota;
import com.example.Mascotas.Entidad.Producto;
import com.example.Mascotas.Servicio.MascotaService;
import com.example.Mascotas.Servicio.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/")
    public String vista() {
        return "index";
    }

    // LISTADO DE MASCOTAS
    @GetMapping("/listaMascotas")
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        return "pages/vistaMascota";
    }

    // LISTADO DE CLIENTES
    @GetMapping("/listaCliente")
    public String listadoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/vistaCliente";
    }

    // LISTADO DE PRODUCTOS
    @GetMapping("/listaProductos")
    public String listadoProductos(Model model) {
        model.addAttribute("producto", new Producto());
        return "pages/vistaProducto";
    }

    // Crear una nueva mascota (mostrar formulario)
    @GetMapping("/vistaMascota")
    public String mostrarFormularioMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "pages/formularioMascota";
    }

    // Crear nueva mascota (guardar)
    @PostMapping("/guardar")
    public String crearMascota(@Valid Mascota mascota, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("mascota", mascota);
            return "pages/formularioMascota";
        }
        mascotaService.guardarMascota(mascota);
        return "redirect:/listaMascotas";
    }

    // ACTUALIZAR: Mostrar el formulario con los datos de la mascota para editar
    @GetMapping("/editar/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        Optional<Mascota> mascota = mascotaService.buscarMascota(id);
        if (mascota.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            return "pages/formularioMascota";
        }
        return "redirect:/listaMascotas";
    }

    // ELIMINAR: Elimina una mascota por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "redirect:/listaMascotas";
    }

    // Crear una nueva mascota para cliente (mostrar formulario)
    @GetMapping("/vistaCliente")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/formularioCliente";
    }

    // Crear nuevo cliente (guardar)
    @PostMapping("/guardarCliente")
    public String guardarCliente(@Valid Cliente cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cliente", cliente);
            return "pages/formularioCliente";
        }
        clienteService.guardarCliente(cliente);
        return "redirect:/listaCliente";
    }

}
