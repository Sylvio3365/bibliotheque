package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.pret.Pret;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PretRepository extends JpaRepository<Pret, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    List<Pret> findByAdherent(Adherent adherent);
}
