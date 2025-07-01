package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.pret.Pret;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository extends JpaRepository<Pret, Integer> {
@Query("SELECT COUNT(p) FROM Pret p WHERE p.adherent.idAdherent = :idAdherent AND p.date_debut <= :nouvelleDate AND (p.date_fin IS NULL OR p.date_fin > :nouvelleDate)")
int countPretsActifsParAdherentALaDate(@Param("idAdherent") Integer idAdherent, @Param("nouvelleDate") LocalDate nouvelleDate);
}
