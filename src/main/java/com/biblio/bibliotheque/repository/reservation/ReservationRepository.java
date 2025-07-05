package com.biblio.bibliotheque.repository.reservation;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsByExemplaire(Exemplaire exemplaire);

    @Query("SELECT r FROM Reservation r JOIN FETCH r.statutReservation sr JOIN FETCH sr.statut")
    List<Reservation> findAllWithStatutReservation();
}
