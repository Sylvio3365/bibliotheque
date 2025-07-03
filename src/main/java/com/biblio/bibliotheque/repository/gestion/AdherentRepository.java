package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.model.pret.Pret;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {

    Optional<Adherent> findByUtilisateur(Utilisateur utilisateur);
 
}
