package com.donateblood.demo.controller;

import com.donateblood.demo.model.Banco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Banco")
public class BancoController {

    private static final List<Banco> bancos = new ArrayList<>();

    @GetMapping
    public String index(Model model) {
        model.addAttribute("banco", new Banco());
        model.addAttribute("bancos", bancos);
        return "index";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Banco banco) {
        bancos.add(banco);
        return "redirect:/";
    }
}
