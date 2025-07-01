package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatExemplaireRepository extends JpaRepository<EtatExemplaire, Integer> {

    // Tu peux ajouter des méthodes personnalisées, par exemple :
    // List<EtatExemplaire> findByExemplaireId(Integer idExemplaire);

}
