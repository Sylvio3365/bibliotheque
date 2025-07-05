package com.biblio.bibliotheque.service.reservation;

import com.biblio.bibliotheque.exception.AdherentNotFoundException;
import com.biblio.bibliotheque.exception.AdherentSanctionedException;
import com.biblio.bibliotheque.exception.ExemplaireNotAvailableException;
import com.biblio.bibliotheque.exception.ExemplaireNotFoundException;
import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.reservation.Reservation;
import com.biblio.bibliotheque.model.sanction.Sanction;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.reservation.ReservationRepository;
import com.biblio.bibliotheque.repository.sanction.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AdherentRepository adherentRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final PretRepository pretRepository;
    private final SanctionRepository sanctionRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, AdherentRepository adherentRepository, ExemplaireRepository exemplaireRepository, PretRepository pretRepository, SanctionRepository sanctionRepository) {
        this.reservationRepository = reservationRepository;
        this.adherentRepository = adherentRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.pretRepository = pretRepository;
        this.sanctionRepository = sanctionRepository;
    }

    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Récupérer une réservation par ID
    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }

    // Enregistrer ou modifier une réservation
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Supprimer une réservation par ID
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }

    public Reservation reserverExemplaire(Integer numExemplaire, Integer numAdherent) {
        // 1. L'adhérent existe ?
        Adherent adherent = adherentRepository.findById(numAdherent)
                .orElseThrow(() -> new AdherentNotFoundException("Adhérent avec l'ID " + numAdherent + " n'existe pas."));

        // 2. L'exemplaire existe ?
        Exemplaire exemplaire = exemplaireRepository.findById(numExemplaire)
                .orElseThrow(() -> new ExemplaireNotFoundException("Exemplaire avec l'ID " + numExemplaire + " n'existe pas."));

        // 3. Le précédent détenteur n'a pas prolongé son prêt ? (l'exemplaire est disponible)
        Optional<Pret> pretEnCours = pretRepository.findByExemplaireAndDateFinAfter(exemplaire, LocalDate.now());
        if (pretEnCours.isPresent()) {
            // Vérifier si le prêt a été prolongé
            // Pour simplifier, on considère qu'un prêt en cours rend l'exemplaire non disponible pour une nouvelle réservation
            // Une logique plus complexe pourrait vérifier si le prolongement est terminé ou non
            throw new ExemplaireNotAvailableException("L'exemplaire " + numExemplaire + " n'est pas disponible car il est actuellement prêté.");
        }

        // 4. L'adhérent n'est pas sanctionné ?
        List<Sanction> sanctionsActives = sanctionRepository.findByAdherentAndDateFinAfter(adherent, LocalDateTime.now());
        if (!sanctionsActives.isEmpty()) {
            throw new AdherentSanctionedException("L'adhérent " + numAdherent + " est sanctionné et ne peut pas faire de réservation.");
        }

        // Si toutes les règles sont respectées, créer la réservation
        Reservation reservation = new Reservation();
        reservation.setExemplaire(exemplaire);
        reservation.setAdherent(adherent);
        reservation.setDate_reservation(LocalDate.now());
        reservation.setDate_debut_reservation(LocalDate.now());
        reservation.setDate_fin_reservation(LocalDate.now().plusDays(7)); // Exemple: réservation pour 7 jours

        return reservationRepository.save(reservation);
    }
}
