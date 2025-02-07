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


    // LISTADO DE MASCOTAS
    @GetMapping("/vista")
    public String vistaMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.verMascotas());
        return "pages/vistaMascota";
    }

    @GetMapping("/listaMascota")
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        return "pages/listadoMascotas";
    }


    // Crear una nueva mascota (mostrar formulario)
    @GetMapping("/vistaMascota")
    public String mostrarFormularioMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "pages/formularioMascota";
    }

    // Crear nueva mascota (guardar)
    @PostMapping("/guardarMascotas")
    public String crearMascota(@Valid Mascota mascota, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("mascota", mascota);
            return "pages/formularioMascota";
        }
        mascotaService.guardarMascota(mascota);
        return "redirect:/listaMascota";
    }

    // ACTUALIZAR: Mostrar el formulario con los datos de la mascota para editar
    @GetMapping("/editarMascotas/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        Optional<Mascota> mascota = mascotaService.buscarMascota(id);
        if (mascota.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            return "pages/formularioMascota";
        }
        return "redirect:/listaMascotas";
    }

    // ELIMINAR: Elimina una mascota por ID
    @GetMapping("/eliminarMascotas/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "redirect:/listaMascota";
    }





}
