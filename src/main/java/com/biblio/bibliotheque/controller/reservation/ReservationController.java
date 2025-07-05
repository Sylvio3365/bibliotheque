package com.biblio.bibliotheque.controller.reservation;

import com.biblio.bibliotheque.exception.AdherentNotFoundException;
import com.biblio.bibliotheque.exception.AdherentSanctionedException;
import com.biblio.bibliotheque.exception.ExemplaireNotAvailableException;
import com.biblio.bibliotheque.exception.ExemplaireNotFoundException;
import com.biblio.bibliotheque.model.reservation.Reservation;
import com.biblio.bibliotheque.repository.reservation.*;
import com.biblio.bibliotheque.service.reservation.ReservationService;

import com.biblio.bibliotheque.repository.pret.*;
import com.biblio.bibliotheque.repository.gestion.*;
import com.biblio.bibliotheque.repository.livre.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String list(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        return "views/reservation/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        return "views/reservation/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Reservation reservation) {
        reservation.setDate_reservation(LocalDate.now());
        reservation.setDate_debut_reservation(LocalDate.now());
        reservation.setDate_fin_reservation(LocalDate.now().plusDays(7)); // Exemple: réservation pour 7 jours
        reservationRepository.save(reservation);
        return "redirect:/reservation";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de réservation invalide : " + id));
        model.addAttribute("reservation", reservation);
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        return "views/reservation/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute Reservation reservation) {
        reservation.setId_reservation(id);
        reservationRepository.save(reservation);
        return "redirect:/reservation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        reservationRepository.deleteById(id);
        return "redirect:/reservation";
    }

    @GetMapping("/reserver")
    public String showReserverForm(Model model) {
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        return "views/reservation/add"; // Reusing the add form for now, can create a dedicated one later
    }

    @PostMapping("/reserver")
    public String reserverExemplaire(@RequestParam("idExemplaire") Integer idExemplaire,
                                     @RequestParam("idAdherent") Integer idAdherent,
                                     RedirectAttributes redirectAttributes) {
        try {
            reservationService.reserverExemplaire(idExemplaire, idAdherent);
            redirectAttributes.addFlashAttribute("successMessage", "Exemplaire réservé avec succès!");
        } catch (AdherentNotFoundException | ExemplaireNotFoundException | ExemplaireNotAvailableException | AdherentSanctionedException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/reservation";
    }
}

