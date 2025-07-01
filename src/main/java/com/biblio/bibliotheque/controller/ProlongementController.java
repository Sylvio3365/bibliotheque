package com.biblio.bibliotheque.controller;

import com.biblio.bibliotheque.model.pret.Prolongement;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.repository.ProlongementRepository;
import com.biblio.bibliotheque.repository.PretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prolongement")
public class ProlongementController {

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Autowired
    private PretRepository pretRepository;

    @GetMapping
    public String listProlongements(Model model) {
        List<Prolongement> prolongements = prolongementRepository.findAll();
        model.addAttribute("prolongements", prolongements);
        return "views/prolongement/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("prolongement", new Prolongement());
        model.addAttribute("prets", pretRepository.findAll());
        return "views/prolongement/add";
    }

    @PostMapping("/add")
    public String addProlongement(@ModelAttribute Prolongement prolongement) {
        prolongementRepository.save(prolongement);
        return "redirect:/prolongement";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Prolongement prolongement = prolongementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid prolongement ID: " + id));
        model.addAttribute("prolongement", prolongement);
        model.addAttribute("prets", pretRepository.findAll());
        return "views/prolongement/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProlongement(@PathVariable("id") Integer id, @ModelAttribute Prolongement prolongement) {
        prolongement.setId_prolongement(id);
        prolongementRepository.save(prolongement);
        return "redirect:/prolongement";
    }

    @GetMapping("/delete/{id}")
    public String deleteProlongement(@PathVariable("id") Integer id) {
        prolongementRepository.deleteById(id);
        return "redirect:/prolongement";
    }
}
