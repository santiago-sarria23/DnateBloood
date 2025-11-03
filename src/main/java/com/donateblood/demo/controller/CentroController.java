package com.donateblood.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import com.donateblood.demo.model.Centro;

@Controller
public class CentroController {

    private static List<Centro> centros = new ArrayList<>();

    @GetMapping("/centros")
    public String index(Model model) {
        model.addAttribute("centro", new Centro());
        model.addAttribute("centros", centros);
        return "centros"; // Cambia el nombre de la vista a "centros.html"
    }

    @PostMapping("/agregarCentro")
    public String agrega(@ModelAttribute Centro centro) {
        centros.add(centro);
        return "redirect:/centros";
    }
}
