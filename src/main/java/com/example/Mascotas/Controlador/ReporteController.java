package com.example.Mascotas.Controlador;

import com.example.Mascotas.Entidad.Cliente;
import com.example.Mascotas.Entidad.Mascota;
import com.example.Mascotas.Entidad.Producto;
import com.example.Mascotas.Entidad.Reporte;
import com.example.Mascotas.Servicio.ClienteService;
import com.example.Mascotas.Servicio.MascotaService;
import com.example.Mascotas.Servicio.ProductoService;
import com.example.Mascotas.Servicio.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class ReporteController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    ProductoService productoService;
    @Autowired
    ReporteService reporteService;

    @GetMapping("/ListarReportes")
    public String listarReportes(Model model) {
        List<Reporte> reportes = reporteService.listarReportes();
        model.addAttribute("reportes", reportes);
        return "pages/listadoReportes";
    }

    @GetMapping("/formularioReporte")
    public String mostrarFormularioRegistro(Model model) {
        List<Cliente> cliente = clienteService.listarClientes();
        model.addAttribute("clientes", cliente);
        List<Producto> producto =productoService.listarProductos();
        model.addAttribute("productos", producto);
        model.addAttribute("reporte", new Reporte());
        return "pages/formularioReportes";
    }

    @PostMapping("/guardarReporte")
    public String guardarReporte(Reporte reporte) {
        reporteService.guardarReporte(reporte);
        return "redirect:/ListarReportes";
    }


    @GetMapping("/editar/{id}")
    public String editarReporte(@PathVariable Long id, Model model) {
        Optional<Reporte> reporte = reporteService.obtenerReportePorId(id);
        if (reporte.isPresent()) {
            model.addAttribute("reporte", reporte.get());
            return "pages/formularioReportes";
        }
        return "redirect:/ListarReportes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return "redirect:/ListarReportes";
    }

}