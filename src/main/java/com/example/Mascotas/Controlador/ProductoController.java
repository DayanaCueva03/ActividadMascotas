package com.example.Mascotas.Controlador;

import com.example.Mascotas.Entidad.Producto;
import com.example.Mascotas.Servicio.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String vista() {
        return "index";
    }

    // LISTADO DE PRODUCTOS
    @GetMapping("/vistaProductos")
    public String vistaProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "pages/vistaProducto";
    }

    @GetMapping("/listaProductos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "pages/listadoProductos";
    }

    // Crear un nuevo producto (mostrar formulario)
    @GetMapping("/vistaProducto")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "pages/formularioProducto";
    }

    // Crear nuevo producto (guardar)
    @PostMapping("/guardar")
    public String crearProducto(@Valid Producto producto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producto", producto);
            return "pages/formularioProducto";
        }
        productoService.guardarProducto(producto);
        return "redirect:/productos/listaProductos";
    }

    // ACTUALIZAR: Mostrar el formulario con los datos del producto para editar
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.buscarProducto(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "pages/formularioProducto";
        }
        return "redirect:/productos/listaProductos";
    }

    // ELIMINAR: Elimina un producto por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos/listaProductos";
    }
}
