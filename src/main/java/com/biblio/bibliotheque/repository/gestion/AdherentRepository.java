package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    @Query("SELECT a FROM Adherent a WHERE a.utilisateur.id = :userId")
    Adherent findByUtilisateurId(@Param("userId") Integer userId);
}
