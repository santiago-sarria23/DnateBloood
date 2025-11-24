package com.donateblood.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/")
    public String landing() {
        return "vistasPagina/landing";
    }

    // @GetMapping("/login")
    // public String login() {
    //     return "logins/login";
    // }

    @GetMapping("/loginadministrador")
    public String loginAdmin() {
        return "logins/administradorLogin";
    }

    @GetMapping("/logincentro") 
    public String loginCentro() {
        return "logins/centroLogin";
    }

    @GetMapping("/loginbanco")
    public String loginBanco() {
    return "logins/bancologin";
    }

    // @GetMapping("/register")
    // public String register() {
    //     return "register/register";
    // }

    @GetMapping("/info")
    public String info() {
        return "vistasPagina/projectInfo";
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
