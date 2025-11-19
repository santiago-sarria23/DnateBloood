package com.donateblood.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/administrador")
    public String loginAdmin() {
        return "administrador";
    }

    @GetMapping("/logincentro") 
    public String loginCentro() {
        return "centroMedico";
    }

    @GetMapping("/loginbanco")
    public String loginBanco() {
        return "bancoSangre";
    }

    @GetMapping("/info")
    public String info() {
        return "info_donate";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contactos";
    }

    @GetMapping("/verBancos")
    public String verBancos() {
        return "verBancos";
    }
    // panels
    @GetMapping("/bancoSangre")
    public String vistaBanco(){
        return "bancoSangre.index";
    }
}
