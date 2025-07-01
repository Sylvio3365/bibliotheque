package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {
    /*
    // Recherche un statut par nom
    Statut findByNom(String nom);

    // Vérifie si un statut existe par nom
    boolean existsByNom(String nom);
    */
}
