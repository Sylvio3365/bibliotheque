package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.StatutAdherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StatutAdherentRepository extends JpaRepository<StatutAdherent, Integer> {

    @Query("""
        SELECT s FROM StatutAdherent s 
        WHERE s.adherent.id = :idAdherent 
        AND (s.dateFin IS NULL OR s.dateFin >= :date) 
        AND s.dateDebut <= :date 
        AND LOWER(s.nom) = 'actif'
    """)
    List<StatutAdherent> findStatutsActifsByAdherentIdAndDate(@Param("idAdherent") Integer idAdherent, @Param("date") LocalDate date);
}
