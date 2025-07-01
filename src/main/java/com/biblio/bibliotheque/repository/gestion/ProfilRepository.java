package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Integer> {
    /*
    // Recherche par nom
    Profil findByNom(String nom);

    // Vérifie l'existence d’un profil avec ce nom
    boolean existsByNom(String nom);
    */
}
