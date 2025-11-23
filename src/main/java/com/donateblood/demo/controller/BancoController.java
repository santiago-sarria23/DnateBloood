package com.donateblood.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/banco")
public class BancoController {

    private static final String USER = "banco";
    private static final String PASS = "1234";

    // Aquí almacenamos las unidades por tipo de sangre
    private static final Map<String, Integer> unidadesSangre = new HashMap<>();

    // LOGIN ---------------------------------------------------------
    @GetMapping("/login")
    public String mostrarLogin() {
        return "bancologin";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if (USER.equals(username) && PASS.equals(password)) {
            return "redirect:/banco/panel";
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "bancologin";
    }

    // PANEL ---------------------------------------------------------
    @GetMapping("/panel")
    public String panel() {
        return "bancopanel";
    }

    // VER UNIDADES ---------------------------------------------------
    @GetMapping("/unidades")
    public String verUnidades(Model model) {
        model.addAttribute("unidades", unidadesSangre);
        return "banco_unidades";
    }

    // MODIFICAR UNIDADES ---------------------------------------------
    @GetMapping("/modificar")
    public String mostrarModificar(Model model) {
        model.addAttribute("tipos", new String[]{
                "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"
        });
        return "banco_modificar";
    }

    @PostMapping("/modificar")
    public String guardarUnidades(
            @RequestParam String tipo,
            @RequestParam int cantidad) {

        unidadesSangre.put(tipo, cantidad);
        return "redirect:/banco/unidades";
    }
}
