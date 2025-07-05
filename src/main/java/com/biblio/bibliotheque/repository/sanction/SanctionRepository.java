
package com.biblio.bibliotheque.repository.sanction;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.sanction.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SanctionRepository extends JpaRepository<Sanction, Integer> {
    @Query("SELECT COUNT(s) > 0 " +
           "FROM Sanction s " +
           "WHERE s.adherent.idAdherent = :idAdherent " +
           "AND :checkDate BETWEEN s.date_debut AND s.date_fin")
    boolean isAdherentSanctioned(@Param("idAdherent") Integer idAdherent, 
                                @Param("checkDate") LocalDateTime checkDate);

    @Query("SELECT s FROM Sanction s WHERE s.adherent = :adherent AND s.date_fin > :dateFin")
    List<Sanction> findByAdherentAndDateFinAfter(@Param("adherent") Adherent adherent, @Param("dateFin") LocalDateTime dateFin);

    @Query(value = "SELECT COUNT(*) > 0 FROM sanction WHERE id_adherent = :idAdherent AND :dateReservation BETWEEN date_debut AND date_fin", nativeQuery = true)
    boolean isAdherentSanctioned(@Param("idAdherent") Integer idAdherent, @Param("dateReservation") Date dateReservation);

}
