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




    @GetMapping("/")
    public String vista() {
        return "index";
    }

    // LISTADO DE MASCOTAS
    @GetMapping("/vistaMascotas")
    public String vistaMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.verMascotas());
        return "pages/vistaMascota";
    }
    @GetMapping("/listaMascota")
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        return "pages/listadoMascotas";
    }
    // LISTADO DE CLIENTES
    @GetMapping("/vistaClientes")
    public String vistaCliente(Model model) {
        model.addAttribute("mascotas", mascotaService.verMascotas());
        return "pages/vistaCliente";
    }

    @GetMapping("/listaCliente")
    public String listadoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/listadoClientes";
    }

    // LISTADO DE PRODUCTOS
    @GetMapping("/vistaProductos")
    public String vistaProducto(Model model) {
        model.addAttribute("mascotas", mascotaService.verMascotas());
        return "pages/vistaProducto";
    }

    @GetMapping("/listaProducto")
    public String listadoProductos(Model model) {
        model.addAttribute("producto", new Producto());
        return "pages/listadoProductos";
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



}
