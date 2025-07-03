package com.biblio.bibliotheque.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.gestion.Adherent;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    /*
    // Exemple de recherche par nom ou prénom
    Adherent findByNomAndPrenom(String nom, String prenom);

    // Exemple : trouver par id utilisateur (utile pour lien compte connecté)
    Adherent findByUtilisateurId(Integer id_utilisateur);
    */
}
