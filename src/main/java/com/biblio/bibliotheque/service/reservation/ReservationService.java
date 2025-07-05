
package com.biblio.bibliotheque.service.reservation;

import com.biblio.bibliotheque.model.gestion.Statut;
import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.reservation.Reservation;
import com.biblio.bibliotheque.model.reservation.StatutReservation;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import com.biblio.bibliotheque.repository.gestion.StatutRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import com.biblio.bibliotheque.repository.livre.TypeRepository;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.reservation.ReservationRepository;
import com.biblio.bibliotheque.repository.reservation.StatutReservationRepository;
import com.biblio.bibliotheque.repository.sanction.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StatutReservationRepository statutReservationRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private SanctionRepository sanctionRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private StatutRepository statutRepository;

    @Autowired
    private TypeRepository typeRepository;

    public void creerReservation(Integer idLivre, Integer idAdherent, Date dateReservation) throws Exception {
        // Find an available exemplaire for the given livre
        Integer idExemplaire = exemplaireRepository.findAvailableExemplaire(idLivre);

        if (idExemplaire == null) {
            throw new Exception("Aucun exemplaire disponible pour ce livre.");
        }

        // Check if the adherent is sanctioned
        if (sanctionRepository.isAdherentSanctioned(idAdherent, dateReservation)) {
            throw new Exception("L'adhérent est sanctionné et ne peut pas réserver.");
        }

        // Check if the book is already loaned out for the requested date
        if (pretRepository.isLivrePrete(idLivre, dateReservation)) {
            throw new Exception("Le livre est déjà en prêt pour la date demandée.");
        }

        Reservation reservation = new Reservation();
        reservation.setExemplaire(exemplaireRepository.findById(idExemplaire).get());
        reservation.setAdherent(adherentRepository.findById(idAdherent).get());
        reservation.setDate_reservation(LocalDate.now());
        reservation.setDate_debut_reservation(dateReservation.toLocalDate());
        reservation.setDate_fin_reservation(dateReservation.toLocalDate().plusDays(7)); // 7 days reservation
        reservationRepository.save(reservation);

        StatutReservation statutReservation = new StatutReservation();
        statutReservation.setReservation(reservation);
        Statut enAttente = statutRepository.findById(1).get(); // 1 = en attente
        statutReservation.setStatut(enAttente);
        statutReservation.setDate_modif(LocalDateTime.now());
        statutReservationRepository.save(statutReservation);
    }

    public void validerReservation(Integer idReservation) throws Exception {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new Exception("Réservation non trouvée"));

        // Create a new Pret
        Pret pret = new Pret();
        pret.setExemplaire(reservation.getExemplaire());
        pret.setAdherent(reservation.getAdherent());
        pret.setDate_debut(reservation.getDate_debut_reservation());
        pret.setDate_fin(reservation.getDate_debut_reservation().plusDays(14)); // 14 days loan
        Type maison = typeRepository.findById(1).get(); // 1 = Maison
        pret.setType(maison);
        pretRepository.save(pret);

        // Update the reservation status
        StatutReservation statutReservation = new StatutReservation();
        statutReservation.setReservation(reservation);
        Statut valide = statutRepository.findById(2).get(); // 2 = valider
        statutReservation.setStatut(valide);
        statutReservation.setDate_modif(LocalDateTime.now());
        statutReservationRepository.save(statutReservation);
    }
}
