package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.pret.StatusProlongement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusProlongementRepository extends JpaRepository<StatusProlongement, Integer> {
    // Tu peux ajouter des méthodes personnalisées ici si besoin
}
