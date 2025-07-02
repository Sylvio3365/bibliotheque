package com.biblio.bibliotheque.repository.reservation;
import java.time.LocalDateTime;

import com.biblio.bibliotheque.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

@Query("""
    SELECT COUNT(r) FROM Reservation r
    WHERE r.exemplaire.id_exemplaire = :idExemplaire
      AND r.date_debut_reservation <= :dateFin
      AND r.date_fin_reservation >= :dateDebut
""")
int countReservationsConflict(
    @Param("idExemplaire") Integer idExemplaire,
    @Param("dateDebut") LocalDateTime dateDebut,
    @Param("dateFin") LocalDateTime dateFin
);



}
