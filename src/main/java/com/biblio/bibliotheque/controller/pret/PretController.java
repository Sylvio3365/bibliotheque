package com.biblio.bibliotheque.controller.pret;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.service.gestion.AdherentService;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import com.biblio.bibliotheque.repository.livre.TypeRepository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/preter")
public class PretController {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private AdherentService adherentService;

    @GetMapping("/formpreter/livre")
    public String showFormPreterLivre(Model model) {
        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentService.getAll()); // Utiliser service
        model.addAttribute("types", typeRepository.findAll());
        return "views/preter/form_preter";
    }
@PostMapping("/add")
public String savePret(@ModelAttribute Pret pret, Model model) {
    Integer idAdherent = pret.getAdherent().getIdAdherent();
    LocalDate dateDebut = pret.getDate_debut();

    String statut = adherentService.getStatutAdherentOnDate(idAdherent, dateDebut);

    model.addAttribute("dateDebut", dateDebut);
    model.addAttribute("idAdherent", idAdherent);
    model.addAttribute("statut", statut);

    if ("actif".equals(statut)) {
        model.addAttribute("message", "Prêt reçu : adhérent actif.");
    } else {
        model.addAttribute("message", "Prêt reçu : adhérent inactif.");
    }

    return "views/preter/verification_pret";
}


}
