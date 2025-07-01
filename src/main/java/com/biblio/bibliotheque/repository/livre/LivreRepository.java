package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
    /*
    // Recherche par titre (exact ou partiel)
    List<Livre> findByTitreContainingIgnoreCase(String titre);

    // Recherche par auteur (exact ou partiel)
    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
    */
}
