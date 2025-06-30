package com.biblio.bibliotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    @GetMapping("/")
    public String afficherHello(Model model) {
        model.addAttribute("message", "Bienvenue à la bibliothèque !");
        return "/views/hello";
    }
}
