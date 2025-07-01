package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
 
}
