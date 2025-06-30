package com.biblio.bibliotheque.repository;

import com.biblio.bibliotheque.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByUsernameAndMdp(String username, String mdp);
}
