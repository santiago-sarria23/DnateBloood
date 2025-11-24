package com.donateblood.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class citaController {

    // Muestra el formulario
    @RequestMapping("/agendarCita")
    public String mostrarFormulario() {
        return "agendar_cita";
    }

    // Recibe los datos del formulario
    @PostMapping("/guardar-cita")
    public String guardarCita(
            @RequestParam("nombre") String nombre,
            @RequestParam("documento") String documento,
            @RequestParam("edad") int edad,
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("banco") String banco,
            @RequestParam("tipo_sangre") String tipoSangre,
            @RequestParam("fecha") String fecha,
            @RequestParam("hora") String hora,
            @RequestParam(value = "observaciones", required = false) String observaciones
    ) {

        // Puedes imprimirlo en consola para verificar
        System.out.println("=== Nueva Cita Registrada ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Documento: " + documento);
        System.out.println("Edad: " + edad);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Banco: " + banco);
        System.out.println("Tipo de sangre: " + tipoSangre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Observaciones: " + observaciones);

        // Luego lo mandas al panel
        return "redirect:/panel";
    }
}
