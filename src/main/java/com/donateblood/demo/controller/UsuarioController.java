package com.donateblood.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.donateblood.demo.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {

    private static List<Usuario> usuarios = new ArrayList<>();

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        usuarios.add(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("mensaje", "Registro exitoso. ¡Bienvenido!");
        return "donantePanel";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute Usuario usuario, Model model) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(usuario.getUsername()) && u.getPassword().equals(usuario.getPassword())) {
                model.addAttribute("usuario", u);
                return "donantePanel";
            }
        }
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }
}

