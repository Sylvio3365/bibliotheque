package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.JourFerie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JourFerieRepository extends JpaRepository<JourFerie, Integer> {
    //Ajoute ici les fonctions necessaires
    @Query("SELECT j.dateJf FROM JourFerie j")
    List<LocalDate> getDatesJourFerie();
}
