package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.pret.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RenduRepository extends JpaRepository<Rendu, Integer> {
    // Requête personnalisée si nécessaire
}
