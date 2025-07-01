package com.biblio.bibliotheque.controller.pret;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.service.gestion.AdherentService;
import com.biblio.bibliotheque.service.livre.ExemplaireService;
import com.biblio.bibliotheque.service.pret.PretService;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import com.biblio.bibliotheque.repository.livre.TypeRepository;
import com.biblio.bibliotheque.service.sanction.SanctionService;
import java.util.Optional;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private PretService pretService;

    @Autowired
    private SanctionService sanctionService;

    @GetMapping("/formpreter/livre")
    public String showFormPreterLivre(Model model) {
        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentService.getAll());
        model.addAttribute("types", typeRepository.findAll());
        return "views/preter/form_preter";
    }

    @PostMapping("/add")
    public String savePret(@ModelAttribute Pret pret, Model model) {
        Integer idAdherent = pret.getAdherent().getIdAdherent();
        LocalDate dateDebut = pret.getDate_debut();
        Integer idExemplaire = pret.getExemplaire().getId_exemplaire();

        String statut = adherentService.getStatutAdherentOnDate(idAdherent, dateDebut);
        boolean disponible = exemplaireService.isExemplaireDisponible(idExemplaire);
        Optional<Adherent> optionalAdherent = adherentService.getById(idAdherent);
        boolean isSanctioned = sanctionService.isAdherentSanctioned(idAdherent, dateDebut.atStartOfDay());

        if (optionalAdherent.isEmpty()) {
            model.addAttribute("message", "Adhérent introuvable.");
            return "views/preter/verification_pret";
        }

        Adherent adherent = optionalAdherent.get();

        int nbMaxPrets = adherent.getProfil().getRegle().getNb_livre_preter_max();
        int nbPretsActifs = pretService.countPretsActifsParAdherentALaDate(idAdherent, dateDebut);

        model.addAttribute("dateDebut", dateDebut);
        model.addAttribute("idAdherent", idAdherent);
        model.addAttribute("statut", statut);
        model.addAttribute("idExemplaire", idExemplaire);
        model.addAttribute("disponible", disponible);
        model.addAttribute("isSanctioned", isSanctioned);

        if (isSanctioned) {
            model.addAttribute("message", "L'adhérent est sanctionné à cette date.");
        } else if (nbPretsActifs >= nbMaxPrets) {
            model.addAttribute("message", "L'adhérent a déjà atteint la limite de prêts (" + nbMaxPrets + ").");
        } else if (!disponible) {
            model.addAttribute("message", "L'exemplaire n'est pas disponible.");
        } else if ("actif".equalsIgnoreCase(statut)) {
            model.addAttribute("message", "Prêt reçu : exemplaire disponible, adhérent actif.");
        } else {
            model.addAttribute("message", "Prêt reçu : exemplaire disponible, mais adhérent inactif.");
        }

        return "views/preter/verification_pret";
    }
}