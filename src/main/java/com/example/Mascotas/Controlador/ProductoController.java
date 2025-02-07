package com.example.Mascotas.Controlador;

import com.example.Mascotas.Entidad.Cliente;
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
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    // LISTADO DE PRODUCTOS
    @GetMapping("/vistaProductos")
    public String vistaProducto(Model model) {
        model.addAttribute("productos", productoService.verProductos());
        return "pages/vistaProducto";
    }

    @GetMapping("/listaProductos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "pages/listadoProductos";
    }
    // Crear un nuevo cliente (mostrar formulario)
    @GetMapping("/vistaProducto")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "pages/formularioProducto";
    }


    // Crear nuevo producto (guardar)
    @PostMapping("/guardarProductos")
    public String crearProducto(@Valid Producto producto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producto", producto);
            return "pages/formularioProducto";
        }
        productoService.guardarProducto(producto);
        return "redirect:/listaProductos";
    }

    // ACTUALIZAR: Mostrar el formulario con los datos del producto para editar
    @GetMapping("/editarProductos/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.buscarProducto(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "pages/formularioProducto";
        }
        return "redirect:/listaProductos";
    }

    // ELIMINAR: Elimina un producto por ID
    @GetMapping("/eliminarProductos/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/listaProductos";
    }
}
