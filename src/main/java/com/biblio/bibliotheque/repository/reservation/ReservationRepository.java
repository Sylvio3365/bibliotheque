package com.biblio.bibliotheque.repository.reservation;

import com.biblio.bibliotheque.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Exemples de requêtes personnalisées possibles :
    // List<Reservation> findByAdherentId(Integer idAdherent);
    // List<Reservation> findByExemplaireId(Integer idExemplaire);
}
