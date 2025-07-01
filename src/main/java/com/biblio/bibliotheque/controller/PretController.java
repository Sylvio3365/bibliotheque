package com.biblio.bibliotheque.controller;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
