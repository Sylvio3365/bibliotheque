package com.biblio.bibliotheque.controller.pret;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblio.bibliotheque.model.pret.Rendu;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.pret.RenduRepository;
import com.biblio.bibliotheque.service.gestion.AdherentService;
import com.biblio.bibliotheque.service.pret.PretService;
import com.biblio.bibliotheque.service.gestion.JourFerieService;
import com.biblio.bibliotheque.service.gestion.RegleJourFerieService;

@Controller
@RequestMapping("/rendu")
public class RenduController {

    @Autowired
    private RenduRepository renduRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private PretService pretService;

    @Autowired
    private JourFerieService jourFerieService;

    @Autowired
    private RegleJourFerieService regleJourFerieService;

    @GetMapping
    public String listRendus(Model model) {
        List<Rendu> rendus = renduRepository.findAll();
        model.addAttribute("rendus", rendus);
        return "views/rendu/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("rendu", new Rendu());
        model.addAttribute("prets", pretRepository.findAll());
        return "views/rendu/add";
    }

    @PostMapping("/add")
    public String addRendu(@ModelAttribute Rendu rendu) {
        renduRepository.save(rendu);
        return "redirect:/rendu";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Rendu rendu = renduRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rendu ID: " + id));
        model.addAttribute("rendu", rendu);
        model.addAttribute("prets", pretRepository.findAll());
        return "views/rendu/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateRendu(@PathVariable("id") Integer id, @ModelAttribute Rendu rendu) {
        rendu.setId_rendu(id);
        renduRepository.save(rendu);
        return "redirect:/rendu";
    }

    @GetMapping("/delete/{id}")
    public String deleteRendu(@PathVariable("id") Integer id) {
        renduRepository.deleteById(id);
        return "redirect:/rendu";
    }

    @GetMapping("/retour")
    public String PageRetour(Model model) {
        model.addAttribute("retour", "Bienvenue dans la page retour de livre");
        return "views/rendu/retour_livre";
    }

    @GetMapping("/valider")
    public String traiterFormulaire(
            @RequestParam("idAdherent") Integer idAdherent,
            @RequestParam("idPret") Integer idPret,
            Model model) {
        try {
            boolean existAdherent = adherentService.isExistAdherent(idAdherent);
            boolean existPret = pretService.existById(idPret);

            if (!existAdherent) {
                model.addAttribute("message", "Erreur : l'adhérent avec l'ID " + idAdherent + " n'existe pas.");
                model.addAttribute("messageType", "error");
            } else if (!existPret) {
                model.addAttribute("message", "Erreur : le prêt avec l'ID " + idPret + " n'existe pas.");
                model.addAttribute("messageType", "error");
            } else {
                boolean correspond = pretService.isPretPourAdherent(idPret, idAdherent);
                if (!correspond) {
                    model.addAttribute("message", "Erreur : ce prêt n'appartient pas à cet adhérent.");
                    model.addAttribute("messageType", "error");
                } else {
                    LocalDate dateFinPret = pretService.getDateFinPret(idPret);
                    LocalDate dateRendu = LocalDate.now();
                    int comportement = regleJourFerieService.getLastComportement();

                    // Calcul date limite ajustée avec isWeekend et isJourFerie
                    LocalDate dateLimite = dateFinPret;

                    // Fonction fictive à adapter selon ta classe/utilitaire
                    while (jourFerieService.isWeekend(dateLimite) || jourFerieService.isJourFerie(dateLimite)) {
                        if (comportement == 1) {
                            dateLimite = dateLimite.plusDays(1); // Avance jusqu'au jour ouvrable
                        } else {
                            dateLimite = dateLimite.minusDays(1); // Recule jusqu'au jour ouvrable
                        }
                    }

                    boolean enRetard = dateRendu.isAfter(dateLimite);

                    if (enRetard) {
                        model.addAttribute("message", "📛 Rendu en retard ! Une sanction peut s'appliquer.");
                        model.addAttribute("messageType", "error");
                    } else {
                        model.addAttribute("message", "✅ Rendu dans les délais. Aucun problème !");
                        model.addAttribute("messageType", "success");
                    }
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", "Erreur inattendue lors de la vérification.");
            model.addAttribute("messageType", "error");
            e.printStackTrace();
        }

        return "views/rendu/retour_livre";
    }

}
