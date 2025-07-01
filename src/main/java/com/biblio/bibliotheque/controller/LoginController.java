package com.biblio.bibliotheque.controller;

import com.biblio.bibliotheque.service.gestion.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String loginForm() {
        return "login/login"; // templates/login/login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String mdp,
            Model model,
            HttpSession session) {
        return utilisateurService.login(username, mdp).map(utilisateur -> {
            session.setAttribute("user", utilisateur);
            session.setAttribute("role", utilisateur.getRole().getNom());
            return "redirect:/hello";
        }).orElseGet(() -> {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login/login";
        });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
