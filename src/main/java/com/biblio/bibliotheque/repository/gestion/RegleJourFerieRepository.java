package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.RegleJourFerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegleJourFerieRepository extends JpaRepository<RegleJourFerie, Integer> {
    
    // Ajoute ici des méthodes personnalisées si nécessaire
}
