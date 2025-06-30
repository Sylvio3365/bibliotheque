package com.biblio.bibliotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.biblio.bibliotheque.model.Utilisateur;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class HelloController {

    @GetMapping("/")
    public String afficherHello(Model model) {
        model.addAttribute("message", "Bienvenue à la bibliothèque !");
        return "/views/hello";
    }

    @GetMapping("/hello")
    public String hello(HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        if (utilisateur == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", utilisateur.getUsername());
        model.addAttribute("role", utilisateur.getRole().getNom());
        return "/views/hello"; // templates/hello.html
    }
}
