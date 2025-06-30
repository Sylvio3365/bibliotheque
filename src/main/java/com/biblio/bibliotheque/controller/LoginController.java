package com.biblio.bibliotheque.controller;

import com.biblio.bibliotheque.model.Utilisateur;
import com.biblio.bibliotheque.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Affichage du formulaire de connexion
    @GetMapping("/login")
    public String loginForm() {
        return "login/login"; // templates/login/login.html
    }

    // Traitement du formulaire de connexion
    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String mdp,
            Model model,
            HttpSession session) {
        return utilisateurService.login(username, mdp).map(utilisateur -> {
            session.setAttribute("user", utilisateur);
            session.setAttribute("role", utilisateur.getRole().getNom());
            return "redirect:/hello"; // redirection vers la page hello
        }).orElseGet(() -> {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login/login";
        });
    }

    // Page d'accueil après connexion
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

    // Déconnexion
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
