package com.donateblood.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import com.donateblood.demo.model.Donante;

@Controller
public class DonanteController {

    private static List<Donante> donantes = new ArrayList<>();

    @GetMapping("/donantes")
    public String index(Model model) {
        model.addAttribute("donante", new Donante());
        model.addAttribute("donantes", donantes);
        return "index";
    }

    @PostMapping("/agregar")
    public String agrega(@ModelAttribute Donante donante) {
        donantes.add(donante);
        return "redirect:/";
    }
}
