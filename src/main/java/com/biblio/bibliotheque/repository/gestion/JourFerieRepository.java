package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourFerieRepository extends JpaRepository<JourFerie, Integer> {
    /*
    // Vérifie s’il existe un jour férié à une date donnée
    boolean existsByDateJf(LocalDate date);

    // Cherche un jour férié par sa date exacte
    Optional<JourFerie> findByDateJf(LocalDate date);
    */
}
