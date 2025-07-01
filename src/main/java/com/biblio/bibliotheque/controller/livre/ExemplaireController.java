package com.biblio.bibliotheque.controller.livre;

import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.reservation.Reservation;
import com.biblio.bibliotheque.service.livre.ExemplaireService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/exemplaires")
public class ExemplaireController {

    @Autowired
    private ExemplaireService exemplaireService;

    @GetMapping("/liste")
    public String afficherListeExemplairesAvecEtat(Model model, HttpSession session) {
        List<EtatExemplaire> etatExemplaires = exemplaireService.getExemplairesAvecDernierEtat();
        model.addAttribute("etatExemplaires", etatExemplaires);

        Object utilisateur = session.getAttribute("user");
        if (utilisateur == null) {
            return "redirect:/login"; // si pas connecté, rediriger vers login
        }
        model.addAttribute("utilisateurConnecte", utilisateur);

        return "/exemplaires/liste";
    }

    @GetMapping("/reserver")
    public String pageDeReservation(@RequestParam("id") Integer idExemplaire, Model model) {
        Exemplaire exemplaire = exemplaireService.getExemplaireById(idExemplaire)
                .orElseThrow(() -> new RuntimeException("Exemplaire introuvable avec l'id: " + idExemplaire));

        EtatExemplaire etat = exemplaireService.findDernierEtatByExemplaireId(idExemplaire);

        model.addAttribute("etat", etat.getEtat());
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("exemplaire", exemplaire);

        return "/exemplaires/reservation";
    }

    
}
