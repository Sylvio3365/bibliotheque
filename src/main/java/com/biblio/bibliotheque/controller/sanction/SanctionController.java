package com.biblio.bibliotheque.controller.sanction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.biblio.bibliotheque.repository.sanction.*;
import com.biblio.bibliotheque.service.sanction.SanctionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/Sanction")
public class SanctionController {

    private final SanctionService sanctionService;

    @Autowired
    public SanctionController(SanctionService sanctionService) {
    this.sanctionService = sanctionService;
    }

    @GetMapping("/sanctionner")
    public String showForm() {
        return "views/sanction_form"; 
    }

    @PostMapping("/sanctionner")
    public String sanctionnerAdherent(
        @RequestParam Integer num_adherent,
        @RequestParam String date_debut,
        Model model
    ) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime debut = LocalDateTime.parse(date_debut, formatter);

        sanctionService.SanctionnerAdherent(num_adherent, debut);
        model.addAttribute("message", "Sanction enregistrée avec succès !");
    } catch (Exception e) {
        model.addAttribute("error", "Erreur : " + e.getMessage());
    }
    return "views/sanction_form";
}
}
