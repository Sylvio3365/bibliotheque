package com.biblio.bibliotheque.controller.pret;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.gestion.Regle;
import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.repository.pret.*;

import jakarta.servlet.http.HttpSession;

import com.biblio.bibliotheque.repository.gestion.*;
import com.biblio.bibliotheque.repository.livre.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private TypeRepository typeRepository;

    // LIST
    @GetMapping
    public String listPrets(Model model) {
        List<Pret> prets = pretRepository.findAll();
        model.addAttribute("prets", prets);
        return "views/pret/list";
    }

    // ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        model.addAttribute("types", typeRepository.findAll());
        return "views/pret/add";
    }

    // ADD ACTION
    @PostMapping("/add")
    public String addPret(@ModelAttribute Pret pret) {
        pretRepository.save(pret);
        return "redirect:/pret";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Pret pret = pretRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("pret", pret);
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        model.addAttribute("types", typeRepository.findAll());
        return "views/pret/edit";
    }

    // UPDATE ACTION
    @PostMapping("/edit/{id}")
    public String updatePret(@PathVariable("id") Integer id, @ModelAttribute Pret pret) {
        pret.setId_pret(id);
        pretRepository.save(pret);
        return "redirect:/pret";
    }

    // DELETE ACTION
    @GetMapping("/delete/{id}")
    public String deletePret(@PathVariable("id") Integer id) {
        pretRepository.deleteById(id);
        return "redirect:/pret";
    }

    @GetMapping("/liste")
    public String listPretsByAdherent(Model model, HttpSession session) {
        // Récupérer l'utilisateur depuis la session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");

        // Si pas d'utilisateur en session, rediriger vers login (optionnel)
        if (utilisateur == null) {
            return "redirect:/login"; 
        }

        // Trouver l'adhérent lié à cet utilisateur
        Adherent adherent = adherentRepository.findByUtilisateur(utilisateur)
                .orElse(null); 

        if (adherent == null) {
            model.addAttribute("error", "Aucun adhérent trouvé");
            return "pret/list"; 
        }

        // Récupérer les prêts de cet adhérent
        List<Pret> prets = pretRepository.findByAdherent(adherent);

        model.addAttribute("prets", prets);
        return "pret/list";
    }

    // Dans PretController.java
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFound(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // Créez une page error.html pour afficher les messages d'erreur
    }

}
