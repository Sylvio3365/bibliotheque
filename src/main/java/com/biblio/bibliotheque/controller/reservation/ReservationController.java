package com.biblio.bibliotheque.controller.reservation;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.gestion.Statut;
import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.reservation.Reservation;
import com.biblio.bibliotheque.model.reservation.StatutReservation;
import com.biblio.bibliotheque.repository.reservation.*;
import com.biblio.bibliotheque.service.gestion.StatutService;

import jakarta.servlet.http.HttpSession;

import com.biblio.bibliotheque.repository.pret.*;
import com.biblio.bibliotheque.repository.gestion.*;
import com.biblio.bibliotheque.repository.livre.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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
    private StatutRepository statutRepository;

    @PostMapping("/reserver")
    public String reserver(
            @RequestParam("id_exemplaire") Integer idExemplaire,
            @RequestParam("id_etat") Integer idEtat,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
                    .orElseThrow(() -> new IllegalArgumentException("Exemplaire invalide"));

            // Créer la réservation
            Reservation reservation = new Reservation();
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");

            if (utilisateur == null) {
                return "redirect:/login"; // si pas connecté, rediriger vers login
            }
            Adherent a = adherentRepository.findByUtilisateurId(utilisateur.getId_utilisateur());
            reservation.setExemplaire(exemplaire);
            reservation.setDate_reservation(LocalDateTime.now());
            reservation.setAdherent(a);
            Reservation savedReservation = reservationRepository.save(reservation);

            // Créer le statut de réservation
            StatutReservation statut = new StatutReservation();
            statut.setReservation(savedReservation);

            // Méthode recommandée (1) - Utilisation de JpaRepository
            Statut s = statutRepository.findById(idEtat).orElse(null);
            statut.setStatut(s);
            statut.setDate_modif(LocalDateTime.now()); // Pour la date + heure

            redirectAttributes.addFlashAttribute("success", "Réservation effectuée avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la réservation: " + e.getMessage());
        }

        return "redirect:/exemplaires/reserver";
    }

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
        reservation.setDate_reservation(LocalDateTime.now());
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
}
