package com.example.Mascotas.Entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El tipo de mascota es obligatorio.")
    private String tipo;

    @Min(value = 0, message = "La edad debe ser un número positivo.")
    private int edad;

    @NotBlank(message = "La raza es obligatoria.")
    private String raza;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es obligatorio.") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio.") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El tipo de mascota es obligatorio.") String getTipo() {
        return tipo;
    }

    public void setTipo(@NotBlank(message = "El tipo de mascota es obligatorio.") String tipo) {
        this.tipo = tipo;
    }

    @Min(value = 0, message = "La edad debe ser un número positivo.")
    public int getEdad() {
        return edad;
    }

    public void setEdad(@Min(value = 0, message = "La edad debe ser un número positivo.") int edad) {
        this.edad = edad;
    }

    public @NotBlank(message = "La raza es obligatoria.") String getRaza() {
        return raza;
    }

    public void setRaza(@NotBlank(message = "La raza es obligatoria.") String raza) {
        this.raza = raza;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
