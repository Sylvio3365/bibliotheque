package com.biblio.bibliotheque.controller;

import com.biblio.bibliotheque.model.pret.Rendu;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.repository.RenduRepository;
import com.biblio.bibliotheque.repository.PretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rendu")
public class RenduController {

    @Autowired
    private RenduRepository renduRepository;

    @Autowired
    private PretRepository pretRepository;

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
}
