package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    /*
    // Recherche un exemplaire par son code unique
    Exemplaire findByCode(String code);

    // Tu peux ajouter d'autres méthodes, par exemple chercher par livre
    // List<Exemplaire> findByLivreId(Integer idLivre);
    */
}
